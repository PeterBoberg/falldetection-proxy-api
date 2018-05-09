package se.frost.falldetectionproxyapi.service;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import se.frost.falldetectionproxyapi.entities.DbEntity;
import se.frost.falldetectionproxyapi.exceptions.ApiException;
import se.frost.falldetectionproxyapi.exceptions.ExceptionType;

@Service
public abstract class AbstractCrudService<T extends DbEntity> implements CrudService<T> {

    @Override
    public T create(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public T get() {
//        Optional<T> entity = getRepository().findById(id);
//        if (!entity.isPresent())
//            throw new ApiException(ExceptionType.RESOURCE_NOT_FOUND);
//        return entity.get();
        return null;
    }

    @Override
    public T update(T entity) {
        assertExisting(entity.getId());
        return getRepository().save(entity);
    }

    @Override
    public void delete() {
//        assertExisting(id);
//        getRepository().deleteById(id);
    }

    protected void assertExisting(long id) {
        if (!getRepository().existsById(id))
            throw new ApiException(ExceptionType.RESOURCE_NOT_FOUND);
    }

    protected abstract CrudRepository<T, Long> getRepository();
}
