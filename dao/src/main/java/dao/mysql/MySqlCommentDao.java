package dao.mysql;

import beans.Comment;
import dao.dao.BaseDbDao;
import dao.dao.ICommentDao;
import dao.dao.NullableHelper;
import exeption.DataAccessException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySqlCommentDao extends BaseDbDao<Comment, Integer> implements ICommentDao {
    protected DataSource dataSource;

    private static Logger logger = Logger.getLogger(MySqlCommentDao.class);

    protected String attachmentsTable = "`newsportal`.`comments`";

    public MySqlCommentDao(DataSource dataSource) {
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
        return "SELECT `id`, `user_id`, `news_id`, `comment`, `date` FROM " + attachmentsTable;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + attachmentsTable + " (`id`, `user_id`, `news_id`, `comment`, `date`) "
                + "VALUES (?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + attachmentsTable + " SET `comment`=?, WHERE `id`=?;";
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
    protected List<Comment> parseResultSet(ResultSet rs) throws DataAccessException {
        ArrayList<Comment> result = new ArrayList<Comment>();
        try {
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(NullableHelper.getInt("id", rs));
                comment.setUser_id(NullableHelper.getInt("user_id", rs));
                comment.setNews_id(NullableHelper.getInt("news_id", rs));
                comment.setComment(rs.getString("comment"));
                comment.setDate(rs.getDate("date"));
                result.add(comment);
            }
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Comment comment)
            throws DataAccessException {
        try {
            NullableHelper.setInt(statement, 1, comment.getId());
            NullableHelper.setInt(statement, 2, comment.getUser_id());
            NullableHelper.setInt(statement, 3, comment.getNews_id());
            statement.setString(4, comment.getComment());
            statement.setDate(5, convertDateToSql(comment.getDate()));
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Comment comment)
            throws DataAccessException {
        try {
            statement.setString(1, comment.getComment());
            NullableHelper.setInt(statement, 2, comment.getId());
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Comment> getCommentByUser(String user) throws DataAccessException {
        if (user == null) {
            return null;
        }
        List<Comment> list;
        String sql = getSelectQuery();
        sql += " WHERE user = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, user);
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
