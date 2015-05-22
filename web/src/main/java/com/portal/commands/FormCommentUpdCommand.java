package com.portal.commands;

import beans.Comment;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exception.DataAccessException;
import exeption.ModelException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FormCommentUpdCommand extends FrontCommand {

    public static String NAME = "FormCommentUpd";
    Logger logger = Logger.getLogger(FormCommentUpdCommand.class);

    @Override
    public void process() throws ServletException, IOException, DataAccessException, ModelException {
        logger.info("FormCommentUpdCommand begin");
        HttpSession session = request.getSession();
        Integer comId = Integer.valueOf(request.getParameter("comId"));
        Comment updcomment = BlManager.getCommentManager().getByKey(comId);
        session.setAttribute("updcomment", updcomment);
        forward(Paths.UPDCOMMENT);
    }
}
