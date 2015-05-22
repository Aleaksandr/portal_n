package com.portal.commands;

import beans.Comment;
import beans.New;
import beans.User;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exception.DataAccessException;
import exeption.ModelException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class IndexCommand extends FrontCommand {

    public static String NAME = "Index";
    Logger logger = Logger.getLogger(IndexCommand.class);
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        New newsitem = null;
        List newslist;
        List userlist;
        List commentlist;
        String item_id = (request.getParameter("item_id") == null)? (String) session.getAttribute("item_id"):
                request.getParameter("item_id");
        item_id = (item_id == null)? "1": item_id;

        logger.info("IndexCommand: begin");
        try {
            newsitem = BlManager.getNewsManager().getByKey(Integer.valueOf(item_id));
            newslist = BlManager.getNewsManager().getAll();
            userlist = BlManager.getUserManager().getAll();

            commentlist = BlManager.getCommentManager().getCommentsByItem(Integer.valueOf(item_id));

            session.setAttribute("commentlist", commentlist);
            session.setAttribute("newsitem", newsitem);
            session.setAttribute("newslist", newslist);
            session.setAttribute("userlist", userlist);
            session.setAttribute("item_id", item_id);

            forward(Paths.INDEX);

        } catch (ModelException e) {
            logger.error(e);
        } catch (DataAccessException e) {
            logger.error(e);
        } catch (ServletException e) {
            logger.info("Error:" + e);
        }
    }
}
