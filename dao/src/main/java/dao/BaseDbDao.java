package dao;

import exception.PersistException;
import org.apache.log4j.Logger;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.DaoConstants;
import util.HibernateUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Abstract class for database 'newportal'
 */

public abstract class BaseDbDao<T> implements GenericDao<T> {

    private static Logger logger = Logger.getLogger(BaseDbDao.class);


   // private SessionFactory sessionFactory;
   // private final ThreadLocal sessions = new ThreadLocal();
   // private final ThreadLocal needClean = new ThreadLocal(){{set(new Boolean(false));}};
    //private boolean needClean = false;
    protected Session session;


    public BaseDbDao() {
        /*HibernateUtil util = HibernateUtil.getHibernateUtil();
        sessionFactory = util.getSessionFactory();*/
    }

    @Override
    public Session getSession() {
        return HibernateUtil.getHibernateUtil().getSession();
    }




   /* public void clearSession(ThreadLocal sessionStatus) {
       // boolean cleaner = true;
        Session session = (Session) sessions.get();
        boolean cleaner = (boolean) sessionStatus.get();
        if (cleaner) {
            if ((session != null) && (session.isOpen())) {
                logger.info("Session CLEAN!!!");
                session.clear();
            }
            sessionStatus.set(false);
        }
        sessions.set(session);
    }*/

    /**
     * Parses ResultSet and returns a list of relevant content ResultSet.
     */
  //  protected abstract List<T> parseResultSet(Session session) throws PersistException;

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

    public T getByCriterria(String fieldValue) throws PersistException {
        T t = null;
        Class classT = getPersistentClass();
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(getPersistentClass());
            t = (T) criteria.add(Restrictions.eq("name", fieldValue)).uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error getByCriteria (Name)in Dao " + e);
            throw new PersistException(e);
        }
        return t;
    }

    public List<T> loadAll() throws PersistException {
        List<T> tAll;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(getPersistentClass());
            tAll = criteria.list();
        } catch (HibernateException e) {
            logger.error("Error loadAll " + getPersistentClass() + " in Dao" + e);
            throw new PersistException(e);
        }
        return tAll;
    }

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
            logger.error(DaoConstants.Const.ERROR_UPDATE_ENTITY, e);
            throw new PersistException(e);
        }
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void cleanSession(Boolean needClean) {
       HibernateUtil.getHibernateUtil().cleanSession(needClean);
    }
}
