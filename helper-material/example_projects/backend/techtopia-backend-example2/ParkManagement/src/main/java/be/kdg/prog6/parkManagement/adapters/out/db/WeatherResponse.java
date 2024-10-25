package be.kdg.prog6.parkManagement.adapters.out.db;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private String date;
    private String weatherType;
    private String temperatureType;
}
