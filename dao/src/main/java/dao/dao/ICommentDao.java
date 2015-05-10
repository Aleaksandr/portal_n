package dao.dao;

import beans.Comment;
import exception.DataAccessException;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended interface class for CommentDao
 */

public interface ICommentDao extends GenericDao<Comment, Integer> {

    List<Comment> getCommentByUser(String user)throws DataAccessException;
}