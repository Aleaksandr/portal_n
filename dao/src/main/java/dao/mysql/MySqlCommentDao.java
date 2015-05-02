package dao.mysql;

import dao.dao.ICommentDao;
import exeption.DataAccessException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by NotePad on 01.05.2015.
 */
public class MySqlCommentDao implements ICommentDao{
    protected DataSource dataSource;

    private static Logger logger = Logger.getLogger(MySqlCommentDao.class);

    public MySqlCommentDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List getCommentAll() throws DataAccessException {
        return null;
    }

    @Override
    public Object getCommentById(int id) throws DataAccessException {
        return null;
    }

    @Override
    public List getCommentByUser() throws DataAccessException {
        return null;
    }

    @Override
    public List getCommentByDate() throws DataAccessException {
        return null;
    }

    @Override
    public Object add(Object object) throws DataAccessException {
        return null;
    }

    @Override
    public void update(Object object) throws DataAccessException {

    }

    @Override
    public Object getByKey(Object key) throws DataAccessException {
        return null;
    }

    @Override
    public List getAll() throws DataAccessException {
        return null;
    }

    @Override
    public void delete(Object object) throws DataAccessException {

    }

    @Override
    public void deleteByKey(Object key) throws DataAccessException {

    }

    @Override
    public void refresh(Object object) throws DataAccessException {

    }
}
