package impl;

import exception.PersistException;
import pojos.User;
import dao.BaseDbDao;
import dao.IUserDao;
import exception.DataAccessException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class to work with User bean and mysql database
 */

public class UserDaoImpl extends BaseDbDao<User> implements IUserDao {

    private static Logger logger = Logger.getLogger(UserDaoImpl.class);

    protected String attachmentsTable = "User";

    public UserDaoImpl() {
        super();
    }


    @Override
    public User getUserByEmail(String email) throws PersistException {
        if (email == null) {
            return null;
        }
        User result = null;
        String hql = "SELECT U FROM User U WHERE U.email = :targetemail";

        try {
            Session session = getSession();
            Query query = session.createQuery(hql);
            query.setParameter("targetemail", email);
            result = (User)query.uniqueResult();
            logger.info("Get user by email: " + result);
        } catch (HibernateException e) {
            logger.error("Error get User by Email in Dao " + e);
            throw new PersistException(e);
        }
        if (result == null) {
            logger.info("getUserByEmail: is Empty ");
            return null;
        }
        return result;
    }

}
