package se.frost.falldetectionproxyapi.repositories;

public interface GenericUserBasedRepository<T> {

    T findByUserId(long id);
}
