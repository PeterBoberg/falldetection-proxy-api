package se.frost.falldetectionproxyapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.frost.falldetectionproxyapi.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String userName);
}
