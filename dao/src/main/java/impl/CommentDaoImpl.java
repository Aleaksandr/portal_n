package impl;

import exception.PersistException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import pojos.Comment;
import pojos.News;
import dao.BaseDbDao;
import dao.ICommentDao;
import exception.DaoException;
import exception.DataAccessException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import pojos.User;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class to work with Comment bean and hibernate
 */

public class CommentDaoImpl extends BaseDbDao<Comment> implements ICommentDao {

    private static Logger logger = Logger.getLogger(CommentDaoImpl.class);

    public CommentDaoImpl() {
        super();
    }

    /** Gets the the appropriate List<Comment> by user parameter value */
    @Override
    public List<Comment> getCommentByUser(User user) throws PersistException {
        List<Comment> commList = null;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(pojos.Comment.class);
            criteria.setCacheable(true);
            commList = criteria.add(Restrictions.eq("news_id", user.getId())).list();
        } catch (HibernateException e) {
            logger.error("Error getCommentByUser Comment in Dao" + e);
            throw new PersistException(e);
        }
        return commList;

    }

    /** Gets the the appropriate List<Comment> by News object */
    @Override
    public List<Comment> getCommentByItem(News nw) throws PersistException {
        List<Comment> commList = null;
        try {
            Session session = getSession();
            commList = nw.getComments();
        } catch (HibernateException e) {
            logger.error("Error getCommentByItem Comment in Dao" + e);
            throw new PersistException(e);
        }
        return commList;
    }
}
