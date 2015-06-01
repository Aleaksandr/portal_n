package main;

import exception.PersistException;
import pojos.News;
import pojos.User;
import com.portal.actionfactory.BlManager;
import exception.DaoException;
import exeption.ModelException;
import org.apache.log4j.Logger;
import util.DaoFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by NotePad on 22.05.2015.
 */
public class MainTest  {



    public static void main( final String[] args ) throws ModelException, DaoException, PersistException {
        Logger logger = Logger.getLogger(MainTest.class);


        News nw = new News();
        nw.setTitle("Title");
        nw.setItem("tewfwyjew");
        nw.setTitle4menu("title4menuuuuuu");
        nw.setAuthor("skfbwker");
        nw.setDate(new Date());

        logger.info("NW: " + nw);

        BlManager.getNewsManager().save(nw);


        nw.setTitle("NewTitle");
        logger.info("Title2 " + nw.getTitle());

        nw = BlManager.getNewsManager().getByKey(3);



     //   BlManager.getNewsManager().remove(nw);





        logger.info("Final is: "+ nw);
   }


}
