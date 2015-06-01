import pojos.Comment;
import exception.DaoException;
import exception.DataAccessException;
import impl.CommentDaoImpl;
import impl.NewsDaoImpl;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NotePad on 22.05.2015.
 */
public class MainTest2 {
    public static void main( final String[] args ) throws DataAccessException, DaoException {
        Logger logger = Logger.getLogger(MainTest2.class);
        Comment cm = new Comment();
        CommentDaoImpl dao = new CommentDaoImpl();
        NewsDaoImpl daoN = new NewsDaoImpl();

        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String dataAdd = format.format(d);


        Comment regCom = new Comment();
      //  regCom = (Comment) dao.getByCriterria("sss");



     /**   regCom.setNews_id(10);
        regCom.setUser_id(10);
        regCom.setComment("bla bla");
        try {
            regCom.setDate(convertToDate(dataAdd));
        } catch (ParseException e) {
            logger.error(e);
        }
      */ // dao.add(regCom);

        System.out.print("Comment is: " + regCom);

    }



    private static Date convertToDate(String dateString) throws ParseException {
        if (dateString == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.parse(dateString.trim());
    }
}
