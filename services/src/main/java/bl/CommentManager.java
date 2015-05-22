package bl;

import beans.Comment;
import exception.DataAccessException;
import exeption.ModelException;

import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Interface of CommentManager
 */

public interface CommentManager extends EntityManager<Comment, Integer> {
    List<Comment> getCommentsByItem (Integer item_id) throws ModelException, DataAccessException;
}
