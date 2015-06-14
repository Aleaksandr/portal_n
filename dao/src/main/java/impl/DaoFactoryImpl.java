package impl;

import util.DaoFactory;
import dao.ICommentDao;
import dao.INewsDao;
import dao.IUserDao;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * It implements the interface methods to connect to the database for domain entity and building factory
 */

public class DaoFactoryImpl implements DaoFactory {
	private static Logger logger = Logger.getLogger(DaoFactoryImpl.class);

	private INewsDao newsDao;
	private IUserDao userDao;
	private ICommentDao commentDao;

	@Override
	public ICommentDao getCommentDao() {
		return commentDao == null ? new CommentDaoImpl() : commentDao;
	}

	@Override
	public INewsDao getNewsDao() {
		return newsDao == null ? new NewsDaoImpl() : newsDao;
	}

	@Override
	public IUserDao getUserDao() {
		return userDao == null ? new UserDaoImpl() : userDao;
	}

}


