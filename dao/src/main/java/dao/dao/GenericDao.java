package dao.dao;

import exception.DataAccessException;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * General interface class fo DAO
 */

public interface GenericDao<T, K> {

    public List<T> getAll() throws DataAccessException;

    public T getByKey(K key) throws DataAccessException;

    public T add(T object) throws DataAccessException;

    public void delete(K key) throws DataAccessException;

    public void update(T object) throws DataAccessException;

}

