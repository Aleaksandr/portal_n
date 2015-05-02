package dao.dao;

import exeption.DataAccessException;

import java.util.List;

public interface ICommentDao<T> extends GenericDao {
    List<T> getCommentAll()throws DataAccessException;
    T getCommentById(int id)throws DataAccessException;
    List<T> getCommentByUser()throws DataAccessException;
    List<T> getCommentByDate()throws DataAccessException;
}