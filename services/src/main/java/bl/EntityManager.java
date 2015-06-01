package bl;

import exception.DaoException;
import exception.PersistException;
import exeption.ModelException;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * General interface of business logic
 */


public interface EntityManager<T> {

	Session getSession();

	void cleanSession(Boolean needClean);

	List<T> loadAll() throws HibernateException, PersistException;

	T getByKey(Serializable id) throws HibernateException, PersistException;

	void save(T entity) throws HibernateException, PersistException;

	T load(Serializable id) throws HibernateException, PersistException;

	void remove(T entity) throws HibernateException, PersistException;

	void update(T entity) throws HibernateException, PersistException;

}
