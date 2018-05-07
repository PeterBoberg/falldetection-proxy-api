package se.frost.falldetectionproxyapi.service;

import org.springframework.stereotype.Service;
import se.frost.falldetectionproxyapi.entities.User;

@Service
public interface UserService extends CrudService<User> {
}
