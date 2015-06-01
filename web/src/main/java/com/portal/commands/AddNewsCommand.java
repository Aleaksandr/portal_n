package com.portal.commands;

import exception.PersistException;
import pojos.News;
import pojos.User;
import com.portal.util.Attributes;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exeption.ModelException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddNewsCommand extends FrontCommand {

    public static String NAME = "AddNews";

    @Override
    public void process() throws ServletException, IOException {

        Logger logger = Logger.getLogger(AddNewsCommand.class);
        HttpSession session = request.getSession();

        News regNews = new News();
        String titleAdd = request.getParameter("title");
        String title4menuAdd = request.getParameter("title4menu");
        String itemAdd = request.getParameter("item");

        User user = (User) session.getAttribute("user");
        regNews.setTitle(titleAdd);
        regNews.setTitle4menu(title4menuAdd);
        regNews.setAuthor(user.getEmail());
        regNews.setDate(new Date());
        regNews.setItem(itemAdd);
        logger.info("Reg News After: " + regNews);

        try {
            BlManager.getNewsManager().save(regNews);

        } catch (PersistException e) {
            logger.error("Error in "+ NAME + e);;
        } finally {
           //BlManager.getNewsManager().cleanSession(true);
        }
        request.setAttribute(Attributes.COMMAND, IndexCommand.NAME);
        forward(Paths.FRONT);
    }
}
