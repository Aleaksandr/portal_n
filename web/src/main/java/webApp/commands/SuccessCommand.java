package webApp.commands;

import storage.DataManager;
import webApp.Attributes;
import webApp.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

public class SuccessCommand extends FrontCommand {

    public static String NAME = "Success";

    @Override
    public void process() throws ServletException, IOException {
        Integer id = Integer.valueOf(super.request.getParameter(Attributes.ID));

        if(DataManager.buyProduct(id)) {
            forward(Paths.SUCCESS);
        } else {
            forward(Paths.ERROR);
        }
    }
}
