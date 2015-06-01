package com.portal.actionfactory;

import com.portal.controller.FrontController;
import exception.DaoException;
import exception.DataAccessException;
import exeption.ModelException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

abstract public class FrontCommand {
    private static Logger logger = Logger.getLogger(FrontCommand.class);
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public void init(ServletContext context,
                     HttpServletRequest request,
                     HttpServletResponse response) {
        this.context = context;
        this.request = request;
        this.response = response;
    }

    abstract public void process() throws ServletException, IOException, DataAccessException, ModelException, DaoException;

    protected void forward(String target) throws ServletException, IOException {
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }
}
