package com.portal.commands;

import beans.User;
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User updUser = new User();
        String emailReg = request.getParameter("email");
        String passReg = request.getParameter("pass");
        User sesUser = (User) session.getAttribute("user");
        Integer id = sesUser.getId();
        String role = sesUser.getRole();
        logger.info("User Email: " + emailReg + " User pass: " + passReg);
        if (StringUtils.isNullOrEmpty(emailReg) || StringUtils.isNullOrEmpty(passReg)) {
            String message = "Some fields are not fill";
            session.setAttribute("message", message);
            logger.info(message);
            request.setAttribute(Attributes.COMMAND, ChangeusersettingCommand.NAME);
            forward(Paths.FRONT);
        } else {
            updUser.setId(id);
            updUser.setEmail(emailReg);
            updUser.setPass(passReg);
            updUser.setRole(role);

            try {
                BlManager.getUserManager().update(updUser);
                session.setAttribute("user", updUser);
                logger.info("Updated User: " + updUser);
            } catch (ModelException e) {
                logger.error(e);
            }
            request.setAttribute(Attributes.COMMAND, IndexCommand.NAME);
            forward(Paths.FRONT);
        }
    }

}
