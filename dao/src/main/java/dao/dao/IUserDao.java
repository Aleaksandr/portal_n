package dao.dao;

import beans.User;
import exeption.DataAccessException;

import java.util.List;

public interface IUserDao extends GenericDao<User, Integer> {

    User getUserByEmail(String email)throws DataAccessException;
}