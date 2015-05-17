package com.portal.commands;

import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exception.DataAccessException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

public class ChangeusersettingCommand extends FrontCommand {

    public static String NAME = "Changeusersetting";
    Logger logger = Logger.getLogger(ChangeusersettingCommand.class);

    @Override
    public void process() throws ServletException, IOException, DataAccessException {
        logger.info("Changeusersetting begin");
        forward(Paths.CHANGEUSERSET);
    }
}
