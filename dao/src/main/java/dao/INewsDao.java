package dao;

import beans.New;
import exception.DataAccessException;
import java.util.Date;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended interface class for NewsDao
 */

public interface INewsDao extends GenericDao<New, Integer> {

    List<New> getNewsByAuthor(String author)throws DataAccessException;
    List<New> getNewsByDate(Date date)throws DataAccessException;
}