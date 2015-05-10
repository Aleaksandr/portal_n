package com.portal.commands;

import beans.New;
import com.portal.Attributes;
import com.portal.BlManager;
import exeption.ModelException;
import org.apache.log4j.Logger;
import storage.DataManager;
import com.portal.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

public class NewsCommand extends FrontCommand {

    public static String NAME = "ProductDetail";

    @Override
    public void process() throws ServletException, IOException {

        Logger logger = Logger.getLogger(NewsCommand.class);
        New nw = null;
       // Integer type = Integer.valueOf(super.request.getParameter(Attributes.TYPE));
        Integer id = Integer.valueOf(super.request.getParameter(Attributes.ID));
        try {
            nw = BlManager.getNewsManager().getByKey(id);
        } catch (ModelException e) {
            logger.info("Get element by key exeption: "+ e);
            e.printStackTrace();
        }

        super.request.setAttribute(Attributes.PRODUCT, nw);

        forward(Paths.INDEX);
    }
}
