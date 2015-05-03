package webApp.commands;

import storage.DataManager;
import storage.entities.Product;
import webApp.Attributes;
import webApp.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

public class ProductDetailCommand extends FrontCommand {

    public static String NAME = "ProductDetail";

    @Override
    public void process() throws ServletException, IOException {
        
        Integer type = Integer.valueOf(super.request.getParameter(Attributes.TYPE));
        Integer id = Integer.valueOf(super.request.getParameter(Attributes.ID));
        Product product = DataManager.getProduct(type, id);

        super.request.setAttribute(Attributes.PRODUCT, product);

        forward(Paths.DETAIL);
    }
}
