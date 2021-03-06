package com.portal.commands;

import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exception.DataAccessException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

public class FormNewsAddCommand extends FrontCommand {

    public static String NAME = "FormNewsAdd";
    Logger logger = Logger.getLogger(FormNewsAddCommand.class);

    @Override
    public void process() throws ServletException, IOException, DataAccessException {
        logger.info("FormNewsAddCommand begin");
        forward(Paths.ADDNEWS);
    }
}
