package se.frost.falldetectionproxyapi.service.users;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import se.frost.falldetectionproxyapi.dto.request.LoginRequest;
import se.frost.falldetectionproxyapi.dto.response.AuthResponse;
import se.frost.falldetectionproxyapi.entities.User;

@Service
public interface UserService extends UserDetailsService {

    AuthResponse register(User user);

    AuthResponse login(LoginRequest request);

    User getCurrentUser();

    User updateCurrentUser(User user);

    void deleteCurrentUser();
}
