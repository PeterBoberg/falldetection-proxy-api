package se.frost.falldetectionproxyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import se.frost.falldetectionproxyapi.entities.User;
import se.frost.falldetectionproxyapi.repositories.UserRepository;

@Component
public class UserServiceImpl extends AbstractCrudService<User> implements UserService {

    private UserRepository userRepository;

    @Override
    protected CrudRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
