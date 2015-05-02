package dao.dao;

import exeption.DataAccessException;
import java.util.List;

public interface INewsDao<T> extends GenericDao {
    List<T> getNewsAll()throws DataAccessException;
    T getNewsById(int id)throws DataAccessException;
    List<T> getNewsByAuthor()throws DataAccessException;
    List<T> getNewsByDate()throws DataAccessException;
}