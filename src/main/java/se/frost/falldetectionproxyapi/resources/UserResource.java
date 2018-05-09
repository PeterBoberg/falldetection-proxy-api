package se.frost.falldetectionproxyapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.frost.falldetectionproxyapi.dto.request.LoginRequest;
import se.frost.falldetectionproxyapi.dto.response.AuthResponse;
import se.frost.falldetectionproxyapi.entities.User;
import se.frost.falldetectionproxyapi.service.users.UserServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserResource {

    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody User user) {
        AuthResponse resp = userService.register(user);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse resp = userService.login(loginRequest);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("It Works!");
    }

    @GetMapping
    public ResponseEntity<User> getLoggedInUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PutMapping
    public ResponseEntity<User> updateLoggedInUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateCurrentUser(user));
    }

    @DeleteMapping
    public ResponseEntity<User> deleteLoggedInUser() {
        userService.deleteCurrentUser();
        return ResponseEntity.ok().build();
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
}
