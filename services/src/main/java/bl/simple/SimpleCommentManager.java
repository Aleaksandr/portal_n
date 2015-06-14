package bl.simple;

import dao.GenericDao;
import exception.PersistException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Comment;
import pojos.News;
import bl.AbstractEntityManager;
import bl.CommentManager;
import dao.ICommentDao;
import exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class of Comment
 */

public class SimpleCommentManager extends AbstractEntityManager<Comment> implements CommentManager {

	private static Logger logger = Logger.getLogger(SimpleCommentManager.class);

	//private final ThreadLocal sessionStatus = new ThreadLocal();
	private Session session;
	private Transaction transaction;

	@Override
	protected ICommentDao getDao() {
		return DaoFactoryProvider.getFactory().getCommentDao();
	}


	@Override
	protected Logger getLogger() {
		return logger;
	}


	@Override
	public List<Comment> getCommentsByItem(News nw) throws PersistException, DataAccessException {
		List<Comment> cList = null;
		try {
			session = getDao().getSession();
			transaction = session.beginTransaction();
			cList = getDao().getCommentByItem(nw);
			transaction.commit();
			session.clear();
		} catch (HibernateException e) {
			logger.error("Error in BL GetCommentBy Item from table " + e);
			transaction.rollback();
		} catch (PersistException e) {
			logger.error(e);
		}
		return cList;
	}
}
