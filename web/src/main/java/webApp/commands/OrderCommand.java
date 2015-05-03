package webApp.commands;

import webApp.Attributes;
import webApp.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

public class OrderCommand extends FrontCommand {

    public static String NAME = "Order";

    @Override
    public void process() throws ServletException, IOException {
        Integer amount = Integer.valueOf(super.request.getParameter(Attributes.PRODUCT_AMOUNT));
        String name = super.request.getParameter(Attributes.PRODUCT_NAME);
        Integer id = Integer.valueOf(super.request.getParameter(Attributes.ID));
        super.request.setAttribute(Attributes.PRODUCT_NAME, name);
        super.request.setAttribute(Attributes.ID, id);

        if(amount!=null && amount>0) {
            forward(Paths.ORDER);
        } else {
            forward(Paths.ERROR);
        }
    }
}
