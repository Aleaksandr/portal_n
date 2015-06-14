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
import java.util.Date;


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
        String fname = request.getParameter("fname");
        String sname = request.getParameter("sname");

        regUser.setId(null);
        regUser.setEmail(emailReg);
        regUser.setPass(passReg);
        regUser.setRole("user");

        UserDetail usDet = new UserDetail();
        usDet.setBDAte(new Date());
        usDet.setFName(fname);
        usDet.setSName(sname);
        usDet.setUser(regUser);

        regUser.setUserDetail(usDet);

        try {
            if(BlManager.getUserManager().getUserByEmail(emailReg) != null){
                String message = "A user with that login ("+ emailReg +") already exists.";
                session.setAttribute("message", message);
                forward(Paths.REGISTER);

            } else {
                BlManager.getUserManager().save(regUser);
                session.setAttribute("user", regUser);
                session.setAttribute("message", null);
                logger.info("Reg User After: " + regUser);
                request.setAttribute(Attributes.COMMAND, CheckCommand.NAME);
                forward(Paths.FRONT);
            }
        } catch (ModelException e) {
            logger.error("Error in "+ NAME + e);
        } catch (DataAccessException e1) {
            logger.error("Error in "+ NAME + e1);
        } catch (PersistException e2) {
            logger.error("Error in "+ NAME + e2);
        }
    }
}
