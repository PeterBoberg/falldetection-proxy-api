package se.frost.falldetectionproxyapi.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import se.frost.falldetectionproxyapi.config.JWTTokenManager;
import se.frost.falldetectionproxyapi.dto.request.LoginRequest;
import se.frost.falldetectionproxyapi.dto.response.AuthResponse;
import se.frost.falldetectionproxyapi.entities.User;
import se.frost.falldetectionproxyapi.repositories.UserRepository;
import se.frost.falldetectionproxyapi.service.AbstractCrudService;

@Component
public class UserServiceImpl extends AbstractCrudService<User> implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder passwordEncoder;
    private JWTTokenManager jwtTokenManager;

    @Override
    public AuthResponse register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        String token = jwtTokenManager.createToken(savedUser);
        return new AuthResponse(token, user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = (User) authentication.getPrincipal();
        String token = jwtTokenManager.createToken(user);
        return new AuthResponse(token, user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User currentUser = userRepository.findByUsername(s);
        if (currentUser == null)
            throw new UsernameNotFoundException("The user does not exist");
        return currentUser;
    }

    @Override
    protected CrudRepository<User, Long> getRepository() {
        return userRepository;
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
    public void setJwtTokenManager(JWTTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }
}
