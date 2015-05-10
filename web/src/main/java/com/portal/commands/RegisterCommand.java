package com.portal.commands;

import com.portal.FrontCommand;
import exception.DataAccessException;

import javax.servlet.ServletException;
import java.io.IOException;

public class RegisterCommand extends FrontCommand {

    public static String NAME = "Register";

    @Override
    public void process() throws ServletException, IOException, DataAccessException {
        forward(Paths.REGISTER);
    }
}
