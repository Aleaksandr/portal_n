package com.portal.commands;

import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exception.DataAccessException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

public class LoginCommand extends FrontCommand {

    public static String NAME = "Login";
    Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public void process() throws ServletException, IOException, DataAccessException {
        logger.info("LogInCommand begin");
        forward(Paths.LOGIN);
    }
}
