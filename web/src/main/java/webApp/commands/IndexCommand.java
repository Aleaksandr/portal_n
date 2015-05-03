package webApp.commands;

import storage.assist.Categories;
import storage.DataManager;
import storage.entities.Product;
import webApp.Attributes;
import webApp.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexCommand extends FrontCommand {

    public static String NAME = "Index";

    @Override
    public void process() throws ServletException, IOException {
        List<Product> productList = DataManager.getProducts();
        Map<Categories, List<Product>> productMap = new HashMap<>();

        for (Categories category : Categories.values()) {
            productMap.put(category, new ArrayList<Product>());
        }

        for(Product product: productList) {
            productMap.get(Categories.parse(product.getType())).add(product);
        }
        
        super.request.setAttribute(Attributes.PRODUCT_MAP, productMap);
        forward(Paths.INDEX);
    }
}
