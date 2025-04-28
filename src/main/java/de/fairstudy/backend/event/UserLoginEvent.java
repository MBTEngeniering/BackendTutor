package de.fairstudy.backend.event;

import lombok.Getter;

@Getter
public class UserLoginEvent {
    private final String username;

    public UserLoginEvent(String username) {
        this.username = username;
    }

}