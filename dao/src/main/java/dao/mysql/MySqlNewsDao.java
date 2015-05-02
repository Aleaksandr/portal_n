package dao.mysql;

import dao.dao.INewsDao;
import exeption.DataAccessException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by NotePad on 01.05.2015.
 */
public class MySqlNewsDao implements INewsDao {

    protected DataSource dataSource;

    private static Logger logger = Logger.getLogger(MySqlNewsDao.class);

    public MySqlNewsDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List getNewsAll() throws DataAccessException {
        return null;
    }

    @Override
    public Object getNewsById(int id) throws DataAccessException {
        return null;
    }

    @Override
    public List getNewsByAuthor() throws DataAccessException {
        return null;
    }

    @Override
    public List getNewsByDate() throws DataAccessException {
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
