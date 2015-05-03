package bl.simple;

import beans.Comment;
import bl.AbstractEntityManager;
import bl.CommentManager;
import dao.dao.ICommentDao;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

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
