package se.frost.falldetectionproxyapi.service;

import org.springframework.stereotype.Service;
import se.frost.falldetectionproxyapi.entities.DbEntity;

@Service
public interface UserBasedCrudService<T extends DbEntity> {

    T createForCurrentUser(T entity);

    T getForCurrentUser();

    T updateForCurrentUser(T entity);

    void deleteForCurrentUser();
}
