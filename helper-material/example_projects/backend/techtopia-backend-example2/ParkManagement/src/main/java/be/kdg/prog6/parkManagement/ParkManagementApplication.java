package be.kdg.prog6.parkManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableRetry(proxyTargetClass = true)
public class ParkManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkManagementApplication.class, args);
    }

}
