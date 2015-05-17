package bl.simple;

import beans.User;
import bl.AbstractEntityManager;
import bl.UserManager;
import dao.IUserDao;
import exception.DataAccessException;
import exeption.ModelException;
import org.apache.log4j.Logger;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class of User
 */


public class SimpleUserManager extends AbstractEntityManager<User, Integer> implements UserManager {

	private static Logger logger = Logger.getLogger(SimpleUserManager.class);
	@Override
	protected IUserDao getDao() {
		return DaoFactoryProvider.getFactory().getUserDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	public User getUserByEmail(String email) throws ModelException, DataAccessException {
		return getDao().getUserByEmail(email);
	}
}