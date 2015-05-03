package webApp.commands;

import webApp.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

public class ErrorCommand extends FrontCommand {

    public void process() throws ServletException, IOException {
        forward(Paths.ERROR);
    }
}
