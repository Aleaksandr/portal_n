package com.portal.commands;

import beans.New;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exception.DataAccessException;
import exeption.ModelException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FormNewsUpdCommand extends FrontCommand {

    public static String NAME = "FormNewsUpd";
    Logger logger = Logger.getLogger(FormNewsUpdCommand.class);

    @Override
    public void process() throws ServletException, IOException, DataAccessException, ModelException {
        logger.info("FormNewsUpdCommand begin");
        HttpSession session = request.getSession();
        Integer newsId = Integer.valueOf((String)session.getAttribute("item_id"));
        New updnews = BlManager.getNewsManager().getByKey(newsId);
        session.setAttribute("updnews", updnews);
        forward(Paths.UPDNEWS);
    }
}
