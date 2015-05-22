package com.portal.commands;

import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exception.DataAccessException;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import java.io.IOException;

public class FormCommentAddCommand extends FrontCommand {

    public static String NAME = "FormCommentAdd";
    Logger logger = Logger.getLogger(FormCommentAddCommand.class);

    @Override
    public void process() throws ServletException, IOException, DataAccessException {
        logger.info("FormCommentAddCommand begin");
        forward(Paths.ADDCOMMENT);
    }
}
