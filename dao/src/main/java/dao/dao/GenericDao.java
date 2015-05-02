package dao.dao;

import exeption.DataAccessException;
import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, K> {

    public T add(T object) throws DataAccessException;

    public void update(T object) throws DataAccessException;

    public T getByKey(K key) throws DataAccessException;

    public List<T> getAll() throws DataAccessException;

    public void delete(T object) throws DataAccessException;

    public void deleteByKey(K key) throws DataAccessException;

    public void refresh(T object) throws DataAccessException;
}

