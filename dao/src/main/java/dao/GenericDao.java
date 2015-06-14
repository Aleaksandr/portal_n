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

    /** Gets the current or open new session */
    Session getSession();

    /** It clean current session */
    void cleanSession(Boolean needClean);

    /** Load a list of all the relevant records in the database */
    public List<T> loadAll() throws PersistException;

    /** Gets the appropriate record with a primary key or a null key */
    public T getByKey(Serializable id) throws PersistException;

    /** It creates a new entry, the corresponding object object */
    public void add(T object) throws PersistException;

    /** Gets the appropriate record with a primary key or a null key */
    T load(Serializable id) throws PersistException;

    /** Removes the entry of the object from the database */
    public void delete(T t) throws PersistException;

    /** It saves the state of the object group in the database */
    public void update(T object) throws PersistException;


}

