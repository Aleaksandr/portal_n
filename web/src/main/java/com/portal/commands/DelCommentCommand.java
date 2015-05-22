package com.portal.commands;

import beans.Comment;
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


public class DelCommentCommand extends FrontCommand {

    public static String NAME = "DelComment";
    Logger logger = Logger.getLogger(DelCommentCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        logger.info("DelCommentCommand begin");
        Integer comId = Integer.valueOf(request.getParameter("comId"));
        logger.info("Com Id: "+comId);

        HttpSession session = request.getSession();

        try {
            BlManager.getCommentManager().remove(comId);
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
