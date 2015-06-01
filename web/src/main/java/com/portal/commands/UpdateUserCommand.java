package com.portal.commands;

import exception.PersistException;
import pojos.User;
import com.mysql.jdbc.StringUtils;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Attributes;
import com.portal.util.Paths;
import exeption.ModelException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserCommand extends FrontCommand {

    public static String NAME = "UpdateUser";
    Logger logger = Logger.getLogger(UpdateUserCommand.class);

    @Override
    public void process() throws ServletException, IOException {

        logger.info("UpdateUserCommand begin");
        HttpSession session = request.getSession();

        String emailReg = request.getParameter("email");
        String passReg = request.getParameter("pass");
        User sesUser = (User) session.getAttribute("user");

        logger.info("User Email: " + emailReg + " User pass: " + passReg);
        if (StringUtils.isNullOrEmpty(emailReg) || StringUtils.isNullOrEmpty(passReg)) {
            String message = "Some fields are not fill";
            session.setAttribute("message", message);
            logger.info(message);
            request.setAttribute(Attributes.COMMAND, ChangeusersettingCommand.NAME);
            forward(Paths.FRONT);
        } else {
            sesUser.setEmail(emailReg);
            sesUser.setPass(passReg);

            try {
                BlManager.getUserManager().update(sesUser);
                session.setAttribute("user", sesUser);
                logger.info("Updated User: " + sesUser);
            } catch (PersistException e) {
                logger.error("Error in "+ NAME + e);;
            }
            request.setAttribute(Attributes.COMMAND, IndexCommand.NAME);
            forward(Paths.FRONT);
        }
    }

}
