package dao.dao;

import beans.New;
import exeption.DataAccessException;

import java.util.Date;
import java.util.List;

public interface INewsDao extends GenericDao<New, Integer> {

    List<New> getNewsByAuthor(String author)throws DataAccessException;
    List<New> getNewsByDate(Date date)throws DataAccessException;
}