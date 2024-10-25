package be.kdg.prog6.parkManagement.adapters.out.db;

import be.kdg.prog6.parkManagement.domain.TemperatureType;
import be.kdg.prog6.parkManagement.domain.WeatherType;
import be.kdg.prog6.parkManagement.ports.in.RefreshmentStandUseCase;
import be.kdg.prog6.parkManagement.ports.in.RefreshmentStandCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class WeatherCheckerService {
    private static final Logger log = LoggerFactory.getLogger(WeatherCheckerService.class);

    private final WebClient.Builder webClientBuilder;


    private final ObjectMapper objectMapper;
    private final RefreshmentStandUseCase refreshmentStandUseCase;

    @Autowired
    public WeatherCheckerService(ObjectMapper objectMapper, RefreshmentStandUseCase refreshmentStandUseCase, WebClient.Builder webClientBuilder) {
        this.objectMapper = objectMapper;
        this.refreshmentStandUseCase = refreshmentStandUseCase;
        this.webClientBuilder = webClientBuilder;
    }


    public WeatherResponse getDefaultWeather() {
        WeatherResponse defaultWeather = new WeatherResponse();
        defaultWeather.setDate(String.valueOf(LocalDate.now()));
        defaultWeather.setWeatherType("SUNNY");
        defaultWeather.setTemperatureType("WARM");
        return defaultWeather;
    }

    public int getDefaultNumberOfVisitors() {
        return 40000;
    }

    public void fallbackWeather(Throwable t) throws JsonProcessingException {
        int numberOfVisitors = calculateNumberOfVisitors();

        int numberOfStandsToOpen = calculateNumberOfStandsToOpenWithHolidayAndWeather(numberOfVisitors, WeatherType.SUNNY, TemperatureType.HOT);

        createRefreshmentStands(numberOfStandsToOpen);
    }

    public HolidayDto fallbackHoliday() {
        HolidayDto defaultHoliday = new HolidayDto();
        defaultHoliday.setEndDate(LocalDate.now());
        defaultHoliday.setName(null);
        defaultHoliday.setNationwide(true);
        return defaultHoliday;
    }

    public int fallbackCalculateVisitors(Exception e) {
        log.error("Fallback method called for calculating the number of visitors: " + e.getMessage());
        return getDefaultNumberOfVisitors();
    }

    @CircuitBreaker(name = "Weather", fallbackMethod = "fallbackWeather")
    @Retryable(retryFor = Exception.class, maxAttempts = 6, backoff = @Backoff(delay = 1000))
    @Scheduled(fixedRate = 86400000)
    public void createStands() throws Exception {
        log.info("dwa");

        WebClient webClient = webClientBuilder.baseUrl("http://localhost:9090").build();
        Mono<String> responseMono = webClient.get()
                .uri("/weather/date/{date}", LocalDate.now())
                .retrieve()
                .bodyToMono(String.class);

        String jsonResponse = responseMono.block();

        WeatherResponse weatherResponse = objectMapper.readValue(jsonResponse, WeatherResponse.class);

        String date = weatherResponse.getDate();
        String weatherType = weatherResponse.getWeatherType();
        String temperatureType = weatherResponse.getTemperatureType();

        log.info("Date: {}", date);
        log.info("Weather Type: {}", weatherType);
        log.info("Temperature Type: {}", temperatureType);

        int numberOfVisitors = calculateNumberOfVisitors();
        int numberOfStandsToOpen = calculateNumberOfStandsToOpenWithHolidayAndWeather(numberOfVisitors, WeatherType.valueOf(weatherType), TemperatureType.valueOf(temperatureType));

        createRefreshmentStands(numberOfStandsToOpen);
    }

    @CircuitBreaker(name = "Visitors", fallbackMethod = "fallbackCalculateVisitors")
    @Retryable(retryFor = Exception.class, maxAttempts = 8, backoff = @Backoff(delay = 1000))
    public int calculateNumberOfVisitors() throws JsonProcessingException {

        WebClient webClient = webClientBuilder.baseUrl("http://localhost:9090").build();
        Mono<String> responseMono = webClient.get()
                .uri("/forecast/date/{date}", LocalDate.now())
                .retrieve()
                .bodyToMono(String.class);

        String jsonResponse = responseMono.block();

        String response = objectMapper.readValue(jsonResponse, String.class);


        int visitors = Integer.parseInt(response);
        log.info(String.valueOf(visitors));

        return visitors;

    }

    public int calculateNumberOfStandsToOpenWithHolidayAndWeather(int numberOfVisitors, WeatherType weatherType, TemperatureType temperatureType) throws JsonProcessingException {
        HolidayDto holiday = getHolidaysForDate();

        boolean isHoliday = holiday.getName() == null;

        int adjustedVisitors = numberOfVisitors;

        if (!isHoliday) {
            log.info("its a holiday");
            adjustedVisitors = (int) (numberOfVisitors * 1.2); // 20% more visitors on holidays
        }

        int numberOfStandsToOpen = 0;

        if (weatherType == WeatherType.CLOUD || weatherType == WeatherType.RAIN || weatherType == WeatherType.STORM) {
            numberOfStandsToOpen = adjustedVisitors / 8000;
        } else if (temperatureType == TemperatureType.HOT || temperatureType == TemperatureType.COLD) {
            numberOfStandsToOpen = adjustedVisitors / 4000;
        }

        return numberOfStandsToOpen;
    }

    @CircuitBreaker(name = "Holiday", fallbackMethod = "fallbackHoliday")
    @Retryable(retryFor = Exception.class, maxAttempts = 6, backoff = @Backoff(delay = 1000))
    public HolidayDto getHolidaysForDate() {
        WebClient webClient = webClientBuilder.baseUrl("http://localhost:9090").build();


        Mono<HolidayDto> responseMono = webClient.get()
                .uri("/holidays/date/{date}", LocalDate.now())
                .retrieve()
                .bodyToMono(HolidayDto.class);

        return responseMono.block();
    }


    private void createRefreshmentStands(int numberOfStandsToOpen) {
        refreshmentStandUseCase.manageRefreshmentStand(new RefreshmentStandCommand(numberOfStandsToOpen));

    }
}
