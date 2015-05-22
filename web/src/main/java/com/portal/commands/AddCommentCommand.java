package com.portal.commands;

import beans.Comment;
import beans.New;
import beans.User;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Attributes;
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


public class AddCommentCommand extends FrontCommand {

    public static String NAME = "AddComment";
    Logger logger = Logger.getLogger(AddCommentCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        logger.info("AddCommentCommand begin");

        HttpSession session = request.getSession();

        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String dataAdd = format.format(d);

        Comment regCom = new Comment();
        User user = (User)session.getAttribute("user");
        Integer user_id = user.getId();
        Integer news_id = Integer.valueOf((String) session.getAttribute("item_id"));
        String comment = request.getParameter("comment");

        regCom.setId(null);
        regCom.setNews_id(news_id);
        regCom.setUser_id(user_id);
        regCom.setComment(comment);
        try {
            regCom.setDate(convertToDate(dataAdd));
        } catch (ParseException e) {
            logger.error(e);
        }


        try {
            BlManager.getCommentManager().save(regCom);
            logger.info("Reg Comment After: "+ regCom);
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
