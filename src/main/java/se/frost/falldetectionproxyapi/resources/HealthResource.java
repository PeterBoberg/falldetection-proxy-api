package se.frost.falldetectionproxyapi.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/health")
public class HealthResource {

    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("It works!");
    }
}
