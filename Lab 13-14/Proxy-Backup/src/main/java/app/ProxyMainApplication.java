package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "app")
public class ProxyMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProxyMainApplication.class, args);
    }
}
