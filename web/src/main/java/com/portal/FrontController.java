package com.portal;


import com.portal.commands.IndexCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        FrontCommand command = null;

        try {
            command = getCommand(req);
            command.init(getServletContext(), req, res);
            command.process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private FrontCommand getCommand(HttpServletRequest req)
            throws Exception {
        try {
            return (FrontCommand) getCommandClass(req).newInstance();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    private Class getCommandClass(HttpServletRequest request) {
        final String CLASS_PATH = "com.portal.commands.";
        HttpSession session = request.getSession();

        Class result;
        final String commandClassName;

        if(request.getParameter(Attributes.COMMAND)==null){
            commandClassName = CLASS_PATH;
            logger.info("Session command: Index");
        } else {
            commandClassName = CLASS_PATH + request.getParameter(Attributes.COMMAND) + "Command";
            logger.info("Session command: "+ request.getParameter("command"));
        }

        try {
            result = Class.forName(commandClassName);

        } catch (Exception e) {
            result = IndexCommand.class;
        }
        return result;
    }
}