package se.frost.falldetectionproxyapi.service;

import org.springframework.stereotype.Service;
import se.frost.falldetectionproxyapi.entities.DbEntity;

@Service
public interface CrudService<T extends DbEntity> {

    T create(T entity);

    T getById(long id);

    T update(T entity);

    void deleteById(long id);
}
