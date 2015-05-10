package dao.mysql;

import beans.User;
import dao.dao.BaseDbDao;
import dao.dao.IUserDao;
import dao.dao.NullableHelper;
import exception.DataAccessException;
import org.apache.log4j.Logger;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class to work with User bean and mysql database
 */

public class MySqlUserDao extends BaseDbDao<User, Integer> implements IUserDao {

    private static Logger logger = Logger.getLogger(MySqlUserDao.class);

    protected String attachmentsTable = "`newsportal`.`users`";

    public MySqlUserDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT `id`, `email`, `pass`, `role` FROM " + attachmentsTable;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + attachmentsTable + " (`id`, `email`, `pass`, `role`) "
                + "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + attachmentsTable + " SET `email`=?, `pass`=?, `role`=? WHERE `id`=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM " + attachmentsTable + " WHERE `id`=?;";
    }

    @Override
    protected String getCountQuery() {
        return "SELECT count(*) FROM " + attachmentsTable;
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws DataAccessException {
        ArrayList<User> result = new ArrayList<User>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(NullableHelper.getInt("id", rs));
                user.setEmail(rs.getString("email"));
                user.setPass(rs.getString("pass"));
                user.setRole(rs.getString("role"));
                result.add(user);
            }
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User user)
            throws DataAccessException {
        try {
            NullableHelper.setInt(statement, 1, user.getId());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPass());
            statement.setString(4, user.getRole());
                    } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User user)
            throws DataAccessException {
        try {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPass());
            statement.setString(3, user.getRole());
            NullableHelper.setInt(statement, 4, user.getId());
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public User getUserByEmail(String email) throws DataAccessException {
        if (email == null) {
            return null;
        }
        List<User> list;
        String sql = getSelectQuery();
        sql += " WHERE email = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, email);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            statement.close();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
        if (list == null || list.isEmpty()) {
            logger.info("getUserByEmail: is Empty ");
            return null;
        }
        if (list.size() > 1) {
            throw new DataAccessException("Received more than one record.");
        }
        return list.iterator().next();
    }
}
