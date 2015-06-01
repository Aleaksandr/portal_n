package bl;

import exception.PersistException;
import pojos.User;
import exception.DataAccessException;
import exeption.ModelException;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Interface of UserManager
 */

public interface UserManager extends EntityManager<User> {

    User getUserByEmail(String email) throws ModelException, DataAccessException, PersistException;
}
