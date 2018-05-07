package se.frost.falldetectionproxyapi.service;

public interface CrudService<T> {

    T create(T entity);
    T getById(long id);
    T update(T entity);
    T deleteById(long id);
}
