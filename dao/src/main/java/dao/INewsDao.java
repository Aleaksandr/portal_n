package dao;

import exception.DaoException;
import exception.PersistException;
import pojos.News;
import exception.DataAccessException;

import java.util.Date;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended interface class for NewsDao
 */

public interface INewsDao extends GenericDao<News> {

    /** Gets the the appropriate List<Comment> by author value parameter */
    List<News> getNewsByAuthor(String author) throws DataAccessException, PersistException;

    /** Gets the the appropriate List<Comment> by date value parameter */
    List<News> getNewsByDate(Date date) throws DataAccessException;

    /** Gets the the appropriate News by title value parameter */
    News getNewsByTitle(String title) throws DataAccessException, PersistException;

    /** Gets the the appropriate List<News> by period */
    public List<News> getNewsByPeriod(Integer first, Integer second) throws PersistException;
}