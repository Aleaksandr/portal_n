package com.portal.commands;

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
        Integer newsId = Integer.valueOf((String)session.getAttribute("item_id"));
        logger.info("Com Id: "+newsId);

        try {
            BlManager.getNewsManager().remove(newsId);
        } catch (ModelException e) {
            logger.error(e);
        }

        request.setAttribute(Attributes.COMMAND, IndexCommand.NAME);
        forward(Paths.FRONT);
    }
}
