package impl;

import beans.New;
import dao.BaseDbDao;
import dao.INewsDao;
import dao.NullableHelper;
import exception.DataAccessException;
import org.apache.log4j.Logger;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class to work with New bean and mysql database
 */

public class NewsDaoImpl extends BaseDbDao<New, Integer> implements INewsDao {

    protected String attachmentsTable = "`newportal`.`news`";

    private static Logger logger = Logger.getLogger(NewsDaoImpl.class);

    public NewsDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    private java.sql.Date convertDateToSql(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    @Override
    public String getSelectQuery() {
        return "SELECT `id`, `title`, `title4menu`, `author`, "
                + "`date`, `item` FROM " + attachmentsTable;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + attachmentsTable + " (`id`, `title`, `title4menu`, `author`, "
                + "`date`, `item`) VALUES (?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + attachmentsTable + " SET `title`=?, `title4menu`=?, "
                + "`item`=? WHERE `id`=?;";
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
    protected List<New> parseResultSet(ResultSet rs) throws DataAccessException {
        ArrayList<New> result = new ArrayList<New>();
        try {
            while (rs.next()) {
                New anew = new New();
                anew.setId(NullableHelper.getInt("id", rs));
                anew.setTitle(rs.getString("title"));
                anew.setTitle4menu(rs.getString("title4menu"));
                anew.setAuthor(rs.getString("author"));
                anew.setDate(rs.getDate("date"));
                anew.setItem(rs.getString("item"));
                result.add(anew);
            }
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, New anew)
            throws DataAccessException {
        try {
            NullableHelper.setInt(statement, 1, anew.getId());
            statement.setString(2, anew.getTitle());
            statement.setString(3, anew.getTitle4menu());
            statement.setString(4, anew.getAuthor());
            statement.setDate(5, convertDateToSql(anew.getDate()));
            statement.setString(6, anew.getItem());
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, New anew)
            throws DataAccessException {
        try {
            statement.setString(1, anew.getTitle());
            statement.setString(2, anew.getTitle4menu());
            statement.setString(3, anew.getItem());
            NullableHelper.setInt(statement, 4, anew.getId());
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<New> getNewsByAuthor(String author) throws DataAccessException {
        if (author == null) {
            return null;
        }
        List<New> list;
        String sql = getSelectQuery();
        sql += " WHERE author = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, author);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            statement.close();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<New> getNewsByDate(Date date) throws DataAccessException {
        if (date == null) {
            return null;
        }
        List<New> list;
        String sql = getSelectQuery();
        sql += " WHERE date = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, date);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            statement.close();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }
}
