package bl.simple;

import com.mysql.jdbc.StringUtils;
import dao.GenericDao;
import exception.PersistException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.User;
import bl.AbstractEntityManager;
import bl.UserManager;
import dao.IUserDao;
import exception.DataAccessException;
import org.apache.log4j.Logger;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class of User
 */


public class SimpleUserManager extends AbstractEntityManager<User> implements UserManager {

	private static Logger logger = Logger.getLogger(SimpleUserManager.class);
	//private final ThreadLocal sessionStatus = new ThreadLocal();
	private Session session;
	private Transaction transaction;


	@Override
	protected IUserDao getDao() {
		return DaoFactoryProvider.getFactory().getUserDao();
	}


	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	public User getUserByEmail(String email) throws DataAccessException, PersistException {
		User us = null;
		if (!(StringUtils.isNullOrEmpty(email))) {
			try {
				session = getDao().getSession();
				transaction = session.beginTransaction();
				us = getDao().getUserByEmail(email);
				transaction.commit();
				session.clear();
			} catch (HibernateException e) {
				logger.error("Error Get Entity by id" + e);
				transaction.rollback();
			} catch (PersistException e) {
				logger.error(e);
			} finally {
				//sessionStatus.set(true);
				//getDao().clearSession(sessionStatus);
			}
		}
		return us;
	}




//	@Override
//	public void clearSession(ThreadLocal sessionStatus) {
//		getDao().clearSession(sessionStatus);
//
//	}
}