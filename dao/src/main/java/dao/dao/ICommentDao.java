package dao.dao;

import beans.Comment;
import exeption.DataAccessException;

import java.util.List;

public interface ICommentDao extends GenericDao<Comment, Integer> {

    List<Comment> getCommentByUser(String user)throws DataAccessException;
}