package com.portal.commands;

import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

public class ErrorCommand extends FrontCommand {

    Logger logger = Logger.getLogger(ErrorCommand.class);
    public void process() throws ServletException, IOException {

        logger.info("ErrorCommand begin");
        forward(Paths.ERROR);
    }
}
