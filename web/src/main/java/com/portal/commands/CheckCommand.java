package com.portal.commands;

import beans.User;
import com.portal.Attributes;
import com.portal.BlManager;
import com.portal.FrontCommand;
import exception.DataAccessException;
import exeption.ModelException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckCommand extends FrontCommand {

    public static String NAME = "Check";

    @Override
    public void process() {
        Logger logger = Logger.getLogger(CheckCommand.class);
        HttpSession session = request.getSession();
        User user;
        String emailLogin = request.getParameter("email");
        String passLogin = request.getParameter("pass");

        try {
            if (BlManager.getUserManager().getUserByEmail(emailLogin) != null) {
                user = BlManager.getUserManager().getUserByEmail(emailLogin);
                if (!user.getPass().matches(passLogin)) {
                    logger.info("pass incorrect, need register!");
                    forward(Paths.REGISTER);
                } else {
                    logger.info("RolE: " + user.getRole());
                    session.setAttribute("user", user);
                    session.setAttribute(Attributes.COMMAND, IndexCommand.NAME);
                    forward(Paths.INDEX);
                }
            } else {
                logger.info("no user, need register!");
                session.setAttribute(Attributes.COMMAND, RegisterCommand.NAME);
                forward(Paths.REGISTER);
            }
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
