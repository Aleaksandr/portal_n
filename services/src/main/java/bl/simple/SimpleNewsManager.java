package bl.simple;

import dao.GenericDao;
import impl.DaoFactoryImpl;
import impl.NewsDaoImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.News;
import bl.AbstractEntityManager;
import bl.NewsManager;
import dao.INewsDao;
import exception.DataAccessException;
import exeption.ModelException;
import org.apache.log4j.Logger;
import util.DaoFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class of News
 */

public class SimpleNewsManager extends AbstractEntityManager<News> implements NewsManager {

	protected static Logger logger = Logger.getLogger(SimpleNewsManager.class);
	//private final ThreadLocal sessionStatus = new ThreadLocal();
	private Session session;
	private Transaction transaction;


	@Override
	protected INewsDao getDao() {
		return DaoFactoryProvider.getFactory().getNewsDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	public List<News> getByDate(Date date) throws ModelException, DataAccessException {
		return null;
	}


	/*@Override
	public void clearSession(ThreadLocal sessionStatus) {
		getDao().clearSession(sessionStatus);
	}*/
}
