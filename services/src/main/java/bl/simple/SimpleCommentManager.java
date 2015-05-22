package bl.simple;

import beans.Comment;
import bl.AbstractEntityManager;
import bl.CommentManager;
import dao.ICommentDao;
import exception.DataAccessException;
import exeption.ModelException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class of Comment
 */

public class SimpleCommentManager extends AbstractEntityManager<Comment, Integer> implements CommentManager {

	private static Logger logger = Logger.getLogger(SimpleCommentManager.class);

	@Override
	protected ICommentDao getDao() {
		return DaoFactoryProvider.getFactory().getCommentDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}


	@Override
	public List<Comment> getCommentsByItem(Integer item_id) throws ModelException, DataAccessException {
		return getDao().getCommentByItem(item_id);
	}
}
