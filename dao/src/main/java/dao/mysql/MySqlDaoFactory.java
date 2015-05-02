package dao.mysql;


import dao.dao.DaoFactory;
import dao.dao.ICommentDao;
import dao.dao.INewsDao;
import dao.dao.IUserDao;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MySqlDaoFactory implements DaoFactory {
	private static Logger logger = Logger.getLogger(MySqlDaoFactory.class);

	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PASSWORD;

	static {
		String filename = "db.properties";
		ClassLoader classLoader = MySqlDaoFactory.class.getClassLoader();
		InputStream input = classLoader.getResourceAsStream(filename);
		Properties properties = new Properties();
		String dbUrl = null;
		String dbUser = null;
		String dbPassword = null;
		try {
			properties.load(input);
			dbUrl = properties.getProperty("url");
			dbUser = properties.getProperty("user");
			dbPassword = properties.getProperty("pass");
		} catch (IOException | NumberFormatException e) {
			logger.error("Failed to get db properties.", e);
		} finally {
			DB_URL = dbUrl == null ? "jdbc:mysql://localhost:3306/newsportal" : dbUrl;
			DB_USER = dbUser == null ? "root" : dbUser;
			DB_PASSWORD = dbPassword == null ? "" : dbPassword;
		}
	}

	private DataSource dataSource;

	private INewsDao newsDao;
	private IUserDao userDao;
	private ICommentDao commentDao;
	//private RelationshipStatusDao relationshipStatusDao;

	public MySqlDaoFactory() {
		dataSource = new DataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USER);
		dataSource.setPassword(DB_PASSWORD);
		dataSource.setInitialSize(5);
		dataSource.setMaxActive(10);
		dataSource.setMaxIdle(5);
		dataSource.setMinIdle(2);
		dataSource.setTimeBetweenEvictionRunsMillis(5000);
		dataSource.setMinEvictableIdleTimeMillis(5000);
	}

	@Override
	public ICommentDao getCommentDao() {
		return commentDao == null ? new MySqlCommentDao(dataSource) : commentDao;
	}

	@Override
	public INewsDao getNewsDao() {
		return newsDao == null ? new MySqlNewsDao(dataSource) : newsDao;
	}

	@Override
	public IUserDao getUserDao() {
		return userDao == null ? new MySqlUserDao(dataSource) : userDao;
	}

}


