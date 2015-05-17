package util;

import dao.ICommentDao;
import dao.INewsDao;
import dao.IUserDao;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Interface for DaoFactory
 */


public interface DaoFactory {
	INewsDao getNewsDao();
	IUserDao getUserDao();
	ICommentDao getCommentDao();
}
