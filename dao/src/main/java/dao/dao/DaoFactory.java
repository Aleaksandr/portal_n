package dao.dao;

public interface DaoFactory {
	INewsDao getNewsDao();
	IUserDao getUserDao();
	ICommentDao getCommentDao();
}
