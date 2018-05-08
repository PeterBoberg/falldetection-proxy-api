package se.frost.falldetectionproxyapi.service.auth;

import org.springframework.stereotype.Component;
import se.frost.falldetectionproxyapi.entities.User;

@Component
public class AuthServiceImpl implements AuthService {

    @Override
    public User getCurrentUser() {
        return null;
    }
}
