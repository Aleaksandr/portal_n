package com.portal.commands;

import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exception.DataAccessException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

public class RegisterCommand extends FrontCommand {

    public static String NAME = "Register";
    Logger logger = Logger.getLogger(RegisterCommand.class);

    @Override
    public void process() throws ServletException, IOException, DataAccessException {
        logger.info("RegisterCommand begin");
        forward(Paths.REGISTER);
    }
}
