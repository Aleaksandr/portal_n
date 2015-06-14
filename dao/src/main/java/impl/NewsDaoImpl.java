package impl;

import dao.GenericDao;
import exception.DaoException;
import exception.PersistException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojos.News;
import dao.BaseDbDao;
import dao.INewsDao;
import exception.DataAccessException;
import org.apache.log4j.Logger;
import util.HibernateUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class to work with News bean and mysql database
 */

public class NewsDaoImpl extends BaseDbDao<News> implements INewsDao {

    private static Logger logger = Logger.getLogger(NewsDaoImpl.class);

    public NewsDaoImpl() {
        super();
    }


    private java.sql.Date convertDateToSql(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    /** Gets the the appropriate List<News> by author value parameter */
    @Override
    public List<News> getNewsByAuthor(String author) throws PersistException {
        List<News> t = null;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(pojos.News.class);
            criteria.setCacheable(true);
            t = (List<News>) criteria.add(Restrictions.eq("author", author)).list();
        } catch (HibernateException e) {
            logger.error("Error getNewsByAuthor NEWS in Dao " + e);
            throw new PersistException(e);
        }
        return (List<News>) t;
    }

    /** Gets the the appropriate News by title value parameter */
    @Override
    public News getNewsByTitle(String title) throws PersistException {
        News t = null;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(pojos.News.class);
            criteria.setCacheable(true);
            t = (News) criteria.add(Restrictions.eq("title", title)).list().get(1);
        } catch (HibernateException e) {
            logger.error("Error getNewsByTitle NEWS in Dao " + e);
            throw new PersistException(e);
        }
        return t;
    }

    /** Gets the the appropriate List<News> by period */
    @Override
    public List<News> getNewsByPeriod(Integer first, Integer second) throws PersistException {
        List<News> t = null;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(pojos.News.class);
            criteria.setCacheable(true);
            criteria.setFirstResult(first);
            criteria.setMaxResults(second);
            t = (List<News>) criteria.list();
        } catch (HibernateException e) {
            logger.error("Error getNewsByAuthor NEWS in Dao " + e);
            throw new PersistException(e);
    }
        return (List<News>) t;
    }

    /** Gets the the appropriate List<Comment> by date value parameter */
    @Override
    public List<News> getNewsByDate(Date date) throws DataAccessException {
        return null;
    }
}
