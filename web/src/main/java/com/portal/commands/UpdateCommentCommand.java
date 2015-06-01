package com.portal.commands;

import exception.PersistException;
import pojos.Comment;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Attributes;
import com.portal.util.Paths;
import exeption.ModelException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class UpdateCommentCommand extends FrontCommand {

    public static String NAME = "UpdateComment";
    Logger logger = Logger.getLogger(UpdateCommentCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        logger.info("UpdateCommentCommand begin");

        HttpSession session = request.getSession();

        Comment updCom;
        updCom = (Comment) session.getAttribute("updcomment");
        String updTextComment = request.getParameter("commentcorrect");
        updCom.setComment(updTextComment);

        try {
            BlManager.getCommentManager().update(updCom);
        } catch (PersistException e) {
            logger.error("Error in "+ NAME + e);;
        }
        request.setAttribute(Attributes.COMMAND, IndexCommand.NAME);
        forward(Paths.FRONT);
    }
}
