package de.fairstudy.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// @TEST
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
