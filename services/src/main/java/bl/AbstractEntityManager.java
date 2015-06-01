package bl;

import com.mysql.jdbc.StringUtils;
import dao.GenericDao;
import exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Abstract class for business logic
 */

public abstract class AbstractEntityManager<T> implements EntityManager<T> {

	private static Logger logger = Logger.getLogger(AbstractEntityManager.class);

	abstract protected Logger getLogger();
	abstract protected GenericDao<T> getDao();
	//private final ThreadLocal sessionStatus = new ThreadLocal();
	private Session session;
	private Transaction transaction;



	@Override
	public List<T> loadAll() {
		List<T> tList = null;
		try {
			session = getDao().getSession();
			transaction = session.beginTransaction();
			tList = getDao().loadAll();
			transaction.commit();
			//session.clear();
		} catch (HibernateException e) {
			logger.error("Error Load List All from table " + e);
			transaction.rollback();
		} catch (PersistException e) {
			logger.error(e);
		} finally {

			//sessionStatus.set(true);
			//getDao().clearSession(sessionStatus);

		}
		return tList;
	}

	@Override
	public T getByKey(Serializable id) {
		T t = null;
		if (!(StringUtils.isNullOrEmpty(String.valueOf(id)))) {
			try {
				session = getDao().getSession();
				transaction = session.beginTransaction();
				t = getDao().getByKey(id);
				transaction.commit();
				//session.clear();
			} catch (HibernateException e) {
				logger.error("Error Get Entity by id" + e);
				transaction.rollback();
			} catch (PersistException e) {
				logger.error(e);
			} finally {
				/*sessionStatus.set(true);
				getDao().clearSession(sessionStatus);*/
			}
		}
		return t;
	}

	@Override
	public T load(Serializable id) {
		T t = null;
		if (!(StringUtils.isNullOrEmpty((String) id))) {
			try {
				session = getDao().getSession();
				transaction = session.beginTransaction();
				t = getDao().load(id);
				transaction.commit();
				//session.clear();

			} catch (HibernateException e) {
				logger.error("Error Load Entity by id" + e);
				transaction.rollback();
			} catch (PersistException e) {
				logger.error(e);
			} finally {

				//sessionStatus.set(true);
				//getDao().clearSession(sessionStatus);
				//session.close();
			}
		}
		return t;
	}


	@Override
	public void save(T t) {
		try {
			session = getDao().getSession();
			transaction = session.beginTransaction();
			getDao().add(t);
			transaction.commit();
			//session.clear();
		} catch (HibernateException e) {
			logger.error("Error Get list of Categories from database" + e);
			transaction.rollback();
		} catch (PersistException e) {
			logger.error(e);
		}finally {

			//sessionStatus.set(true);
			//getDao().clearSession(sessionStatus);
			//session.close();
		}
	}

	@Override
	public void remove(T t) {
		try {
			session = getDao().getSession();
			transaction = session.beginTransaction();
			getDao().delete(t);
			transaction.commit();
			//session.clear();
		} catch (HibernateException e) {
			logger.error("Error Delete Entity from database:   " + e);
			transaction.rollback();
		} catch (PersistException e) {
			logger.error(e);
		}finally {

			//sessionStatus.set(true);
			//getDao().clearSession(sessionStatus);
		}
	}

	@Override
	public void update(T t) {
		try {
			session = getDao().getSession();
			transaction = session.beginTransaction();
			getDao().update(t);
			transaction.commit();
			//session.clear();
		} catch (HibernateException e) {
			logger.error("Error Update Entity from database:   " + e);
			transaction.rollback();
		} catch (PersistException e) {
			logger.error(e);
		}finally {

			//sessionStatus.set(true);
			//getDao().clearSession(sessionStatus);
		}
	}

	@Override
	public Session getSession() {
		return getDao().getSession();
	}

	@Override
	public void cleanSession(Boolean needClean) {
		getDao().cleanSession(needClean);
	}
}
