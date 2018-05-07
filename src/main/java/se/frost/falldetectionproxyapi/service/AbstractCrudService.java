package se.frost.falldetectionproxyapi.service;

public abstract class AbstractCrudService<T> implements CrudService<T>{

    @Override
    public T create(T entity) {
        return null;
    }

    @Override
    public T getById(long id) {
        return null;
    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public T deleteById(long id) {
        return null;
    }
}
