package com.portal.commands;

import beans.New;
import beans.User;
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

                Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String dataAdd = format.format(d);

        New regNews = new New();
        String titleAdd = request.getParameter("title");
        String title4menuAdd = request.getParameter("title4menu");
        String itemAdd = request.getParameter("item");

        User user = (User) session.getAttribute("user");
        regNews.setId(null);
        regNews.setTitle(titleAdd);
        regNews.setTitle4menu(title4menuAdd);
        regNews.setAuthor(user.getEmail());
        try {
            regNews.setDate(convertToDate(dataAdd));
        } catch (ParseException e) {
            logger.error(e);
        }
        regNews.setItem(itemAdd);

        try {
            BlManager.getNewsManager().save(regNews);
            logger.info("Reg New After: "+ regNews);
        } catch (ModelException e) {
            logger.error(e);
        }
        request.setAttribute(Attributes.COMMAND, IndexCommand.NAME);
        forward(Paths.FRONT);
    }

    private Date convertToDate(String dateString) throws ParseException {
        if (dateString == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.parse(dateString.trim());
    }
}
