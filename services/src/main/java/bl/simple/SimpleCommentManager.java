package bl.simple;

import beans.Comment;
import bl.AbstractEntityManager;
import bl.CommentManager;
import dao.dao.ICommentDao;
import org.apache.log4j.Logger;

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


}
