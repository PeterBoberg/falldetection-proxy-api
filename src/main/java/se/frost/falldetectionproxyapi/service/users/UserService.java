package se.frost.falldetectionproxyapi.service.users;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import se.frost.falldetectionproxyapi.dto.request.LoginRequest;
import se.frost.falldetectionproxyapi.dto.response.AuthResponse;
import se.frost.falldetectionproxyapi.entities.User;
import se.frost.falldetectionproxyapi.service.CrudService;

@Service
public interface UserService extends CrudService<User>, UserDetailsService {

    AuthResponse register(User user);

    AuthResponse login(LoginRequest request);
}
