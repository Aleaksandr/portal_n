package webApp;

import webApp.commands.IndexCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String uri = req.getRequestURI();

        if (uri.contains("/resources/")) {
            super.doGet(req, res);
        } else {
            processRequest(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        FrontCommand command;
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
        final String CLASS_PATH = "webApp.commands.";

        Class result;
        final String commandClassName;

        if(request.getParameter(Attributes.COMMAND)==null){
            commandClassName = CLASS_PATH;
        } else {
            commandClassName = CLASS_PATH + request.getParameter(Attributes.COMMAND) + "Command";
        }

        try {
            result = Class.forName(commandClassName);
        } catch (Exception e) {
            result = IndexCommand.class;
        }
        return result;
    }
}

