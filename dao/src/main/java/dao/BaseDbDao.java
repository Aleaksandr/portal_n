package dao;

import exception.PersistException;
import org.apache.log4j.Logger;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 *  An abstract class provides a base implementation CRUD operations using JDBC.
 * @param <T>  type of object persistence
 */

public abstract class BaseDbDao<T> implements GenericDao<T> {

    private static Logger logger = Logger.getLogger(BaseDbDao.class);
    protected Session session;

    public BaseDbDao() {
    }

    @Override
    public Session getSession() {
        return HibernateUtil.getHibernateUtil().getSession();
    }

    @Override
    public void cleanSession(Boolean needClean) {
        HibernateUtil.getHibernateUtil().cleanSession(needClean);
    }

    /**
     * Gets the appropriate record with a primary key or a null key
     * @param id of object, what we get from database
     * @return object from database
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public T getByKey(Serializable id) throws PersistException {
        Class classT = getPersistentClass();
        logger.info("Get "+ classT + " by id:" + id);
        T t = null;
        try {
            Session session = getSession();
            t = (T) session.get(getPersistentClass(), id);
        } catch (HibernateException e) {
            logger.error("Error get " + classT + " in Dao" + e);
            throw new PersistException(e);
        }
        return t;
    }

    /**
     * Method for delete object from database
     * @param t - object of entity for delete
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    @Override
    public void delete(T t) throws PersistException {
        try {
            Session session = getSession();
            session.delete(t);
            logger.info("Delete: " + t);
        } catch (HibernateException e) {
            logger.error("Error delete object from Database: " + e);
            throw new PersistException(e);
        }
    }

    /**
     * load the appropriate record with a primary key
     * @param id of object, what we get from database
     * @return object from database or objectnotfoundexception if object not consist in database
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    public T load(Serializable id) throws PersistException {
        Class classT = getPersistentClass();
        logger.info("Load "+ classT + " by id: " + id);
        T t = null;
        try {
            Session session = getSession();
            t = (T) session.load(getPersistentClass(), id);
            logger.info("load() class: " + t);
            session.isDirty();
        } catch (HibernateException e) {
            logger.error("Error load() " + classT + " in Dao" + e);
            throw new PersistException(e);
        }
        return t;
    }

    /**
     * Gets the object by name parameter
     * @param fieldValue is value of name parameter
     * @return object from database
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    public T getByCriterria(String fieldValue) throws PersistException {
        T t = null;
        Class classT = getPersistentClass();
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(getPersistentClass());
            criteria.setCacheable(true);
            t = (T) criteria.add(Restrictions.eq("name", fieldValue)).uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error getByCriteria (Name)in Dao " + e);
            throw new PersistException(e);
        }
        return t;
    }

    /**
     * Load All proxy objects from database
     * @return list<T> objects
     * @throws PersistException my class of exception, abstracted from relational databases
     */
    public List<T> loadAll() throws PersistException {
        List<T> tAll;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(getPersistentClass());
            criteria.setCacheable(true);
            tAll = criteria.list();
        } catch (HibernateException e) {
            logger.error("Error loadAll " + getPersistentClass() + " in Dao" + e);
            throw new PersistException(e);
        }
        return tAll;
    }

    /** It creates a new entry, the corresponding object object */
    @Override
    public void add(T object) throws PersistException {

        try {
            Session session = getSession();
            session.save(object);
            session.flush();
            logger.info("Add:" + object);
        }  catch (HibernateException e) {
            logger.error("Error save ENTITY in Database" + e);
            throw new PersistException(e);
        }
    }

    /**
     * Method for update object persistence in database
     * @param object - object entity for update persistence in database
     * @throws PersistException my class of exception, abstracted from relational databases
     */

    @Override
    public void update(T object) throws PersistException {
        if (object == null) {
            return;
        }
        try {
            Session session = getSession();
            session.update(object);
            logger.info("Update:" + object);
        } catch (HibernateException e) {
            logger.error("Error update ENTITY in Dao. "+ e);
            throw new PersistException(e);
        }
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
