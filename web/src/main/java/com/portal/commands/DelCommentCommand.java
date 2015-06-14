package com.portal.commands;

import exception.PersistException;
import pojos.Comment;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Attributes;
import com.portal.util.Paths;
import exeption.ModelException;
import org.apache.log4j.Logger;
import pojos.News;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class DelCommentCommand extends FrontCommand {

    public static String NAME = "DelComment";
    Logger logger = Logger.getLogger(DelCommentCommand.class);

    @Override
    public void process() throws ServletException, IOException {

        BlManager.getCommentManager().cleanSession(true);
        logger.info("DelCommentCommand begin");
        Integer comId = Integer.valueOf(request.getParameter("comId"));
        logger.info("Com Id: "+comId);

        HttpSession session = request.getSession();

        try {
            Comment delCom = BlManager.getCommentManager().getByKey(comId);
            News newsWithDelComment = delCom.getNew();
            newsWithDelComment.getComments().remove(delCom);
            BlManager.getCommentManager().remove(delCom);
            BlManager.getNewsManager().update(newsWithDelComment);

        } catch (PersistException e) {
            logger.error("Error in "+ NAME + e);;
        }

        request.setAttribute(Attributes.COMMAND, IndexCommand.NAME);
        forward(Paths.FRONT);
    }
}
