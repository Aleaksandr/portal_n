package com.portal.commands;

import exception.PersistException;
import pojos.User;
import com.portal.util.Attributes;
import com.portal.actionfactory.BlManager;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exception.DataAccessException;
import exeption.ModelException;
import org.apache.log4j.Logger;
import pojos.UserDetail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckCommand extends FrontCommand {

    public static String NAME = "Check";
    Logger logger = Logger.getLogger(CheckCommand.class);

    @Override
    public void process() {
        logger.info("CheckCommand begin");
        String message;
        HttpSession session = request.getSession();
        User userIn;
        UserDetail usDet;
        String type = null;
        String emailLogin = request.getParameter("email");
        String passLogin = request.getParameter("pass");

        try {
            userIn = BlManager.getUserManager().getUserByEmail(emailLogin);
            if (userIn != null) {
                if (!userIn.getPass().matches(passLogin)) {
                    message = "Pass incorrect, need register!";
                    session.setAttribute("message", message);
                    logger.info(message);
                    request.setAttribute(Attributes.COMMAND, RegisterCommand.NAME);
                    forward(Paths.FRONT);
                } else {
                    logger.info("Sign IN (Check): " + userIn.getRole());
                    session.setAttribute("user", userIn);
                }
            } else {
                message = "User incorrect, need register!";
                session.setAttribute("message", message);
                logger.info(message);
                request.setAttribute(Attributes.COMMAND, RegisterCommand.NAME);
                forward(Paths.FRONT);
            }

            User user = (User) session.getAttribute("user");
            if (user == null) {
                type = "GUEST";
            }else if (user.getRole().equals("admin")) {
                type = "ADMINISTRATOR";
            }else if (user.getRole().equals("user")){
                type = "USER";
            }
            session.setAttribute("usertype", type);
            request.setAttribute(Attributes.COMMAND, IndexCommand.NAME);
            forward(Paths.FRONT);

        } catch (PersistException e) {
            logger.error("Error in "+ NAME + e);;
        } catch (DataAccessException e1) {
            logger.error("Error in "+ NAME + e1);
        } catch (IOException e2) {
            logger.error("Error in "+ NAME + e2);
        } catch (ServletException e3) {
            logger.error("Error in "+ NAME + e3);
        } catch (ModelException e5) {
            logger.error("Error in "+ NAME + e5);
        }

    }
}
