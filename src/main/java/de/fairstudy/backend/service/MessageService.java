package de.fairstudy.backend.service;

import de.fairstudy.backend.config.AppConfig;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@RefreshScope
@Service
public class MessageService {

    private final AppConfig config;

    public MessageService(AppConfig config) {
        this.config = config;
    }

    public String getMessage() {
        return config.getMessage();
    }
}