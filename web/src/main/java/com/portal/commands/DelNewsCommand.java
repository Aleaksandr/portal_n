package com.portal.commands;

import exception.PersistException;
import pojos.News;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Attributes;
import com.portal.util.Paths;
import exeption.ModelException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DelNewsCommand extends FrontCommand {

    public static String NAME = "DelNews";
    Logger logger = Logger.getLogger(DelNewsCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        logger.info("DelNewsCommand begin");
        HttpSession session = request.getSession();
        News delNews = (News) session.getAttribute("newsitem");

        try {
            BlManager.getNewsManager().remove(delNews);
            session.setAttribute(Attributes.ITEMID, null);
        } catch (PersistException e) {
            logger.error("Error in "+ NAME + e);;
        }
        logger.info("Removed News: "+ delNews);
        request.setAttribute(Attributes.COMMAND, IndexCommand.NAME);
        forward(Paths.FRONT);
    }
}
