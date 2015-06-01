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

    List<News> getNewsByAuthor(String author) throws DataAccessException, PersistException;

    List<News> getNewsByDate(Date date) throws DataAccessException;

    News getNewsByTitle(String title) throws DataAccessException, PersistException;
}