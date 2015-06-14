package bl.simple;

import dao.GenericDao;
import exception.PersistException;
import impl.DaoFactoryImpl;
import impl.NewsDaoImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Comment;
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
	public List<News> getNewsByAuthor(String author) throws DataAccessException {
		List<News> nList = null;
		try {
			session = getDao().getSession();
			transaction = session.beginTransaction();
			nList = getDao().getNewsByAuthor(author);
			transaction.commit();
		} catch (HibernateException e) {
			logger.error("Error in BL GetCommentBy Item from table " + e);
			transaction.rollback();
		} catch (PersistException e) {
			logger.error(e);
		}
		return nList;
	}

	@Override
	public List<News> getNewsByDate(Date date) {
		return null;
	}

	@Override
	public News getNewsByTitle(String title) throws DataAccessException {
		News nw = null;
		try {
			session = getDao().getSession();
			transaction = session.beginTransaction();
			nw = getDao().getNewsByTitle(title);
			transaction.commit();
		} catch (HibernateException e) {
			logger.error("Error in BL GetCommentBy Item from table " + e);
			transaction.rollback();
		} catch (PersistException e) {
			logger.error(e);
		}
		return nw;
	}

	@Override
	public List<News> getNewsByPeriod(Integer first, Integer second) throws DataAccessException {
		List<News> nList = null;
		try {
			session = getDao().getSession();
			transaction = session.beginTransaction();
			nList = getDao().getNewsByPeriod(first, second);
			transaction.commit();
		} catch (HibernateException e) {
			logger.error("Error in BL GetCommentBy Item from table " + e);
			transaction.rollback();
		} catch (PersistException e) {
			logger.error(e);
		}
		return nList;
	}
}
