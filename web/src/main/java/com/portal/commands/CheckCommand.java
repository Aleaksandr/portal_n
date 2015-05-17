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

public class CheckCommand extends FrontCommand {

    public static String NAME = "Check";
    Logger logger = Logger.getLogger(CheckCommand.class);

    @Override
    public void process() {
        logger.info("CheckCommand begin");
        String message;
        HttpSession session = request.getSession();
        User user;
        String type = null;
        String emailLogin = request.getParameter("email");
        String passLogin = request.getParameter("pass");

        try {
            if (BlManager.getUserManager().getUserByEmail(emailLogin) != null) {
                user = BlManager.getUserManager().getUserByEmail(emailLogin);
                if (!user.getPass().matches(passLogin)) {
                    message = "Pass incorrect, need register!";
                    session.setAttribute("message", message);
                    logger.info(message);
                    request.setAttribute(Attributes.COMMAND, RegisterCommand.NAME);
                    forward(Paths.FRONT);
                } else {
                    logger.info("Sign IN (Check): " + user.getRole());
                    session.setAttribute("user", user);
                }
            } else {
                message = "User incorrect, need register!";
                session.setAttribute("message", message);
                logger.info(message);
                request.setAttribute(Attributes.COMMAND, RegisterCommand.NAME);
                forward(Paths.FRONT);
            }

            user = (User) session.getAttribute("user");
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

        } catch (ModelException e) {
            logger.info(e);
        } catch (DataAccessException e) {
            logger.info(e);
        } catch (IOException e) {
            logger.info(e);
        } catch (ServletException e) {
            logger.info(e);
        }

    }
}
