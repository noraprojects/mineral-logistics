package be.kdg.prog6.parkManagementTest;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

public class ExternalServiceIntegrationTest {

    @Test
    public void callExternalServiceForAYear() {
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(1);

        while (currentDate.isBefore(endDate)) {
            callExternalService(currentDate);
            currentDate = currentDate.plusDays(1);
        }
    }

    private void callExternalService(LocalDate date) {
        try {
            String urlString = "http://localhost:9090/weather/date/" + date;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                String jsonResponse = response.toString();
                processResponse(jsonResponse);
            } else {
                handleErrorResponse(responseCode);
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void processResponse(String jsonResponse) {
        System.out.println("Received response: " + jsonResponse);
    }

    private void handleErrorResponse(int responseCode) {
        System.err.println("HTTP error response. Response code: " + responseCode);
    }

    private void handleException(Exception e) {
        e.printStackTrace();
    }

}
