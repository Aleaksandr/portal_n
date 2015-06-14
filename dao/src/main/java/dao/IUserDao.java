package dao;

import exception.PersistException;
import pojos.User;
import exception.DataAccessException;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended interface class for UserDao
 */

public interface IUserDao extends GenericDao<User> {

    /** Gets the the appropriate User by email parametr value */
    User getUserByEmail(String email) throws DataAccessException, PersistException;
}