package dao;

import exception.PersistException;
import pojos.User;
import exception.DataAccessException;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended interface class for UserDao
 */

public interface IUserDao extends GenericDao<User> {

    User getUserByEmail(String email) throws DataAccessException, PersistException;
}