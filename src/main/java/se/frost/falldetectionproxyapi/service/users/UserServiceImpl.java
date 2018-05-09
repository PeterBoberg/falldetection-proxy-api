package se.frost.falldetectionproxyapi.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import se.frost.falldetectionproxyapi.dto.request.LoginRequest;
import se.frost.falldetectionproxyapi.dto.response.AuthResponse;
import se.frost.falldetectionproxyapi.entities.User;
import se.frost.falldetectionproxyapi.repositories.UserRepository;
import se.frost.falldetectionproxyapi.service.auth.AuthService;
import se.frost.falldetectionproxyapi.service.token.JWTTokenService;

@Component
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder passwordEncoder;
    private JWTTokenService jwtTokenService;
    private AuthService authService;

    @Override
    public AuthResponse register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        String token = jwtTokenService.createToken(savedUser);
        return new AuthResponse(token, hidePasswordForUser(savedUser));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = (User) authentication.getPrincipal();
        String token = jwtTokenService.createToken(user);
        return new AuthResponse(token, hidePasswordForUser(user));
    }

    @Override
    public User getCurrentUser() {
        User current = authService.getCurrentUser();
        return hidePasswordForUser(current);
    }

    @Override
    public User updateCurrentUser(User user) {
        User currentUser = authService.getCurrentUser();
        currentUser.copyFrom(user);
        return hidePasswordForUser(userRepository.save(currentUser));
    }

    @Override
    public void deleteCurrentUser() {
        userRepository.deleteById(authService.getCurrentUser().getId());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User currentUser = userRepository.findByUsername(s);
        if (currentUser == null)
            throw new UsernameNotFoundException("The user does not exist");
        return currentUser;
    }

    private User hidePasswordForUser(User current) {
        current.setPassword("*** Hidden ***");
        return current;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setJwtTokenService(JWTTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
