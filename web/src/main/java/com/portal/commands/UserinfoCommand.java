package com.portal.commands;

import pojos.User;
import com.portal.actionfactory.FrontCommand;
import com.portal.util.Paths;
import exception.DataAccessException;
import org.apache.log4j.Logger;
import pojos.UserDetail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserinfoCommand extends FrontCommand {

    public static String NAME = "Userinfo";
    Logger logger = Logger.getLogger(UserinfoCommand.class);
    UserDetail usDet;

    @Override
    public void process() throws ServletException, IOException, DataAccessException {
        logger.info("UserinfoCommand begin");

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if (user == null) {
            logger.error("Register user is no found");
            forward(Paths.ERROR);
        }else {
            usDet = user.getUserDetail();
            logger.info("UserDetail: "+ usDet);
            session.setAttribute("usDet", usDet);
            forward(Paths.USERINFO);
        }
    }
}
