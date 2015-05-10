package dao.dao;

import beans.Identifiable;
import exception.DataAccessException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Abstract class for database 'newsportal'
 */

public abstract class BaseDbDao<T extends Identifiable, K> implements GenericDao<T, K> {

	protected DataSource dataSource;

	public BaseDbDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

/**
 * Prepared sql query for statement
 */

	protected abstract String getSelectQuery();

	protected abstract String getInsertQuery();

	protected abstract String getUpdateQuery();

	protected abstract String getDeleteQuery();

	protected abstract String getCountQuery();

/**
 *
 * @param rs
 * @return T object, List<T object>
 * @throws DataAccessException
 */
	protected abstract List<T> parseResultSet(ResultSet rs)
			throws DataAccessException;

	protected abstract void prepareStatementForInsert(
			PreparedStatement statement, T object) throws DataAccessException;

	protected abstract void prepareStatementForUpdate(
			PreparedStatement statement, T object) throws DataAccessException;


	@Override
	public T getByKey(K key) throws DataAccessException {
		if (key == null) {
			return null;
		}
		List<T> list;
		String sql = getSelectQuery();
		sql += " WHERE id = ?";
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, key);
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
			statement.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		if (list == null || list.isEmpty()) {
			return null;
		}
		if (list.size() > 1) {
			throw new DataAccessException("Received more than one record.");
		}
		return list.iterator().next();
	}

	@Override
	public List<T> getAll() throws DataAccessException {
		List<T> list;
		String sql = getSelectQuery();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
			statement.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		return list;
	}

	@Override
	public T add(T object) throws DataAccessException {
		if (object == null) {
			return null;
		}
		if (object.getId() != null) {
			throw new DataAccessException("Object is already persist.");
		}
		T persistInstance;
		String sql = getInsertQuery();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			prepareStatementForInsert(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new DataAccessException(
						"More then 1 record modified on insert: " + count);
			}
			statement.close();

			sql = getSelectQuery() + " WHERE id = last_insert_id();";
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			List<T> list = parseResultSet(rs);
			if ((list == null) || (list.size() != 1)) {
				throw new DataAccessException(
						"Exception on findByPK: new data SELECT error.");
			}
			persistInstance = list.iterator().next();
			statement.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		return persistInstance;
	}

	@Override
	public void update(T object) throws DataAccessException {
		if (object == null) {
			return;
		}
		String sql = getUpdateQuery();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			prepareStatementForUpdate(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new DataAccessException(
						"More then 1 record modified on update: " + count);
			}
			statement.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public void delete(K key) throws DataAccessException {
		if (key == null) {
			return;
		}
		String sql = getDeleteQuery();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, key);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new DataAccessException(
						"More then 1 record modified on delete: " + count);
			}
			statement.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
}
