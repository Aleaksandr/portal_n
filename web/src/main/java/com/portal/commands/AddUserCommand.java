package com.portal.commands;

import beans.User;
import com.portal.BlManager;
import com.portal.FrontCommand;
import exeption.ModelException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddUserCommand extends FrontCommand {

    public static String NAME = "AddUser";

    @Override
    public void process() throws ServletException, IOException {

        Logger logger = Logger.getLogger(AddUserCommand.class);
        HttpSession session = request.getSession();
        User regUser = new User();
        String emailReg = request.getParameter("email");
        String passReg = request.getParameter("pass");

        regUser.setId(null);
        regUser.setEmail(emailReg);
        regUser.setPass(passReg);
        regUser.setRole("user");

        try {
            User user = BlManager.getUserManager().save(regUser);
            session.setAttribute("user", user);
            logger.info("Reg User After: "+ user);
        } catch (ModelException e) {
            logger.error(e);
        }
        forward(Paths.INDEX);
    }

}
