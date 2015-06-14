package com.portal.commands;

import exception.PersistException;
import pojos.Comment;
import pojos.News;
import pojos.User;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Attributes;
import com.portal.util.Paths;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AddCommentCommand extends FrontCommand {

    public static String NAME = "AddComment";
    Logger logger = Logger.getLogger(AddCommentCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        logger.info("AddCommentCommand begin");
        BlManager.getCommentManager().cleanSession(true);

        HttpSession session = request.getSession();

        News thisNews = (News) session.getAttribute("newsitem");

        Comment regCom = new Comment();
        User user = (User)session.getAttribute("user");
        Integer user_id = user.getId();
        String comment = request.getParameter("comment");

        regCom.setUser_id(user_id);
        regCom.setComment(comment);
        regCom.setDate(new Date());

        try {
            regCom.setNew(thisNews);
            logger.info("This news: " + thisNews);
            logger.info("This comment: " + regCom);
            if (thisNews.getComments() == null) {
                List<Comment> newComList = new ArrayList<Comment>();
                newComList.add(regCom);
                thisNews.setComments(newComList);
            } else {
                thisNews.getComments().add(regCom);
            }
            BlManager.getCommentManager().save(regCom);
        } catch (PersistException e) {
            logger.error("Error in "+ NAME + e);;
        }
        request.setAttribute(Attributes.COMMAND, IndexCommand.NAME);
        forward(Paths.FRONT);
    }
}
