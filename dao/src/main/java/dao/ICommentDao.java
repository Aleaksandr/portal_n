package dao;

import exception.PersistException;
import pojos.Comment;
import pojos.News;
import exception.DaoException;
import exception.DataAccessException;
import pojos.User;

import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended interface class for CommentDao
 */

public interface ICommentDao extends GenericDao<Comment> {

    List<Comment> getCommentByUser(User user) throws PersistException;

    List<Comment> getCommentByItem(News nw)throws PersistException;
}