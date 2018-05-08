package se.frost.falldetectionproxyapi.dto.response;

import se.frost.falldetectionproxyapi.entities.User;

public class AuthResponse {

    private String accessToken;
    private User user;

    public AuthResponse() {
    }

    public AuthResponse(String accessToken, User user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
