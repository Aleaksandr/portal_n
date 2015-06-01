package impl;

import dao.INewsDao;
import exception.DaoException;
import exception.DataAccessException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojos.News;
import util.HibernateUtil;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by NotePad on 26.05.2015.
 */
public class NewsDaoImplTest {

    private static Logger logger = Logger.getLogger(NewsDaoImplTest.class);
   //private final ThreadLocal sessionStatus = new ThreadLocal();
    News news = null;
    News news2 = null;
    protected Session session;
    protected Transaction transaction;
    INewsDao newsDao;


    Date d = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    String dataAdd = format.format(d);
    String tittle = "titleeee";
    /**

    @Before
    public void add() throws SQLException, ParseException, DataAccessException {
        //DaoFactory factory = new DaoFactoryImpl();
        //transaction = session.beginTransaction();
        session = HibernateUtil.getHibernateUtil().getSession();
        newsDao = new NewsDaoImpl();
        news = new News(tittle, "title4menu", "authorrr", convertToDate(dataAdd), "texttexttext");
        newsDao.add(news);
    }


    @Test
    public void getByKey() throws DataAccessException, DaoException {

        news2 = newsDao.getByKey(news.getId());
        assertEquals(news, news2);
        assertEquals(news.getItem(), news2.getItem());
        logger.info("TEST getByKey success");
    }

   @Test
    public void getNewsByTitle() throws DataAccessException, DaoException {
        news2 = newsDao.getNewsByTitle(tittle);
       logger.info("!!!!!!!!!!!"+ news2);
       logger.info(news2.getTitle());
       //assertEquals(tittle, news2.getTitle());
        logger.info("TEST getByTitle success");

    }
*/

    private Date convertToDate(String dateString) throws ParseException {
        if (dateString == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.parse(dateString.trim());
    }


    @After
    public void tearDown() throws Exception {
        //Integer key = news.getId();
       // newsDao.delete(news);
        //news = null;
        news2 = null;
        //  news3 = null;
        //assertNull(newsDao.getByKey(key));
        //logger.info("TEST delete() success");
        session.clear();
    }
}