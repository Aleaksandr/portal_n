package com.portal.commands;

import com.portal.util.Paginator;
import com.portal.util.Attributes;
import exception.DataAccessException;
import exception.PersistException;
import pojos.News;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exception.DaoException;
import org.apache.log4j.Logger;
import util.Const;

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
    public void process() throws ServletException, IOException, DaoException, DataAccessException {
        HttpSession session = request.getSession();
        Paginator paginator;
        News newsitem;
        List newslist = null;
        List userlist;
        List commentListByItem;
        String pageCommand;
        Integer pageFirst;


        /**
         * Paging
         */
        pageCommand = request.getParameter("page");
        paginator = Paginator.getInstance(session);
        try {
            newslist = paginator.getList(pageCommand);
        } catch (PersistException e) {
            logger.error("Get list exception: "+ e);
        }


        try {
            /**
             * Load News and User lists
             */

            userlist = BlManager.getUserManager().loadAll();

            /**
             * news ID starting display:
             */
            String item_id = (request.getParameter(Attributes.ITEMID) == null)?
                    (String) session.getAttribute(Attributes.ITEMID):request.getParameter(Attributes.ITEMID);
            assert newslist != null;
            News firstEl = (News)newslist.get(0);
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
