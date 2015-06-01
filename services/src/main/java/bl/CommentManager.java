package bl;

import exception.PersistException;
import pojos.Comment;
import pojos.News;
import exception.DaoException;
import exception.DataAccessException;
import exeption.ModelException;

import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Interface of CommentManager
 */

public interface CommentManager extends EntityManager<Comment> {
    List<Comment> getCommentsByItem (News nw) throws ModelException, DataAccessException, DaoException, PersistException;
}
