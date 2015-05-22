package com.portal.commands;

import beans.User;
import com.portal.util.Attributes;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exception.DataAccessException;
import exeption.ModelException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AddUserCommand extends FrontCommand {

    public static String NAME = "AddUser";
    Logger logger = Logger.getLogger(AddUserCommand.class);

    @Override
    public void process() throws ServletException, IOException {

        logger.info("AddUserCommand begin");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User regUser = new User();
        String emailReg = request.getParameter("email");
        String passReg = request.getParameter("pass");

        regUser.setId(null);
        regUser.setEmail(emailReg);
        regUser.setPass(passReg);
        regUser.setRole("user");

        try {
            if(BlManager.getUserManager().getUserByEmail(emailReg) != null){
                String message = "A user with that login ("+ emailReg +") already exists.";
                session.setAttribute("message", message);
                forward(Paths.REGISTER);

            } else {
                User user = BlManager.getUserManager().save(regUser);
                session.setAttribute("user", user);
                session.setAttribute("message", null);
                logger.info("Reg User After: " + user);
                request.setAttribute(Attributes.COMMAND, CheckCommand.NAME);
                forward(Paths.FRONT);
            }
        } catch (ModelException e) {
            logger.error(e);
        } catch (DataAccessException e) {
            logger.error(e);
        }
    }
}
