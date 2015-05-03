package dao.dao;

import exeption.DataAccessException;
import java.util.List;

public interface GenericDao<T, K> {

    public List<T> getAll() throws DataAccessException;

    public T getByKey(K key) throws DataAccessException;

    public T add(T object) throws DataAccessException;

    public void delete(K key) throws DataAccessException;

    public void update(T object) throws DataAccessException;

}

