package se.frost.falldetectionproxyapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.frost.falldetectionproxyapi.entities.User;
import se.frost.falldetectionproxyapi.service.UserServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "users", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserResource  {

    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User saved = userService.create(user);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public String test() {
        return "It works!";
    }


    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
}
