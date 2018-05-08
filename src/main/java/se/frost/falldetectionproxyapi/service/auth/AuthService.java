package se.frost.falldetectionproxyapi.service.auth;

import org.springframework.stereotype.Service;
import se.frost.falldetectionproxyapi.entities.User;

@Service
public interface AuthService {

    User getCurrentUser();
}
