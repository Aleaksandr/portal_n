package dao;

import exception.DaoException;
import exception.DataAccessException;
import exception.PersistException;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * General interface class fo dao
 */

public interface GenericDao<T> {

    Session getSession();

    void cleanSession(Boolean needClean);

    public List<T> loadAll() throws PersistException;

    public T getByKey(Serializable id) throws PersistException;

    public void add(T object) throws PersistException;

    T load(Serializable id) throws PersistException;

    public void delete(T t) throws PersistException;

    public void update(T object) throws PersistException;


}

