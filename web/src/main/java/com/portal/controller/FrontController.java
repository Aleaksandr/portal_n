package com.portal.controller;


import com.portal.actionfactory.FrontCommand;
import com.portal.commands.IndexCommand;
import com.portal.util.Attributes;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public final static String CLASS_PATH = "com.portal.commands.";
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
            logger.info("FrontController begin");
            command.process();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {

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
        HttpSession session = request.getSession();
        String RequestCommandParametr;

        /**
         * requst attribute more priority requst parameters:
         */
        if (request.getAttribute(Attributes.COMMAND) != null) {
            RequestCommandParametr = (String)request.getAttribute(Attributes.COMMAND);
            request.removeAttribute(Attributes.COMMAND);
        } else {
            RequestCommandParametr = request.getParameter(Attributes.COMMAND);
        }

        Class result;
        final String commandClassName;

        if(RequestCommandParametr==null){
            commandClassName = CLASS_PATH;
            logger.info("Session command: Index");
        } else {
            commandClassName = CLASS_PATH + RequestCommandParametr + "Command";
            logger.info("Session command: "+ RequestCommandParametr);
        }

        try {
            result = Class.forName(commandClassName);

        } catch (Exception e) {
            result = IndexCommand.class;
        }
        return result;
    }
}