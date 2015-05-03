package bl.simple;

import beans.User;
import bl.AbstractEntityManager;
import bl.UserManager;
import dao.dao.IUserDao;
import org.apache.log4j.Logger;


public class SimpleUserManager extends AbstractEntityManager<User, Integer> implements UserManager {

	private static Logger logger = Logger.getLogger(SimpleCommentManager.class);
	@Override
	protected IUserDao getDao() {
		return DaoFactoryProvider.getFactory().getUserDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}
}