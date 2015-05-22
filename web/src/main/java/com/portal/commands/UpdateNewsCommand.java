package com.portal.commands;

import beans.New;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Attributes;
import com.portal.util.Paths;
import exeption.ModelException;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class UpdateNewsCommand extends FrontCommand {

    public static String NAME = "UpdateNews";
    Logger logger = Logger.getLogger(UpdateNewsCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        logger.info("UpdateNewsCommand begin");

        HttpSession session = request.getSession();

        New updNews;
        updNews = (New) session.getAttribute("updnews");
        String updTextNews = request.getParameter("newscorrect");
        String updTitleNews = request.getParameter("titlenewscorrect");
        updNews.setTitle(updTitleNews);
        updNews.setItem(updTextNews);

        try {
            BlManager.getNewsManager().update(updNews);
        } catch (ModelException e) {
            logger.error(e);
        }
        request.setAttribute(Attributes.COMMAND, IndexCommand.NAME);
        forward(Paths.FRONT);
    }
}
