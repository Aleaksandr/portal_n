package com.portal.commands;

import com.portal.util.Attributes;
import exception.PersistException;
import pojos.News;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exception.DaoException;
import exeption.ModelException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Class for calculation of parameters displayed on the front.jsp
 */

public class IndexCommand extends FrontCommand {

    public static String NAME = "Index";
    Logger logger = Logger.getLogger(IndexCommand.class);

    @Override
    public void process() throws ServletException, IOException, DaoException {
        HttpSession session = request.getSession();
        News newsitem;
        List newslist;
        List userlist;
        List commentListByItem;

        try {
            /**
             * Load News and User lists
             */
            newslist = BlManager.getNewsManager().loadAll();
            userlist = BlManager.getUserManager().loadAll();

            /**
             * news ID starting display:
             */
            String item_id = (request.getParameter(Attributes.ITEMID) == null)?
                    (String) session.getAttribute(Attributes.ITEMID):request.getParameter(Attributes.ITEMID);
            News firstEl = (News)newslist.get(1);
            item_id = (item_id == null)? String.valueOf(firstEl.getId()): item_id;

            /**
             * Search news and comments by news ID
             */

            newsitem = BlManager.getNewsManager().getByKey(Integer.valueOf(item_id));
            commentListByItem = newsitem.getComments();

           // BlManager.getCommentManager().needClean();

            logger.info("IndexCommand: begin");

            /**
             * Save of information found in the session
             */
            session.setAttribute("commentlist", commentListByItem);
            session.setAttribute("newsitem", newsitem);
            session.setAttribute("newslist", newslist);
            session.setAttribute("userlist", userlist);
            session.setAttribute(Attributes.ITEMID, item_id);

            /**
             * Forwadr to main page: front.jsp
             */
            forward(Paths.INDEX);

        } catch (ServletException e) {
            logger.error("Error in IndexCommang" + e);;
        } catch (PersistException e1) {
            logger.error("Error in IndexCommang" + e1);;
        }
    }
}
