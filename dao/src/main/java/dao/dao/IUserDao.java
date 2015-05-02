package dao.dao;

import exeption.DataAccessException;

import java.util.List;

public interface IUserDao <T> extends GenericDao {
    List<T> getUserAll()throws DataAccessException;
    T getUserById(int id)throws DataAccessException;
    List<T> getUserByEmail()throws DataAccessException;
    List<T> getUserByLogin()throws DataAccessException;

}