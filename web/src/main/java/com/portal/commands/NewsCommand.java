package com.portal.commands;

import beans.New;
import com.portal.util.Attributes;
import com.portal.actionfactory.BlManager;
import com.portal.util.Paths;
import exeption.ModelException;
import org.apache.log4j.Logger;
import com.portal.actionfactory.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

public class NewsCommand extends FrontCommand {

    public static String NAME = "ProductDetail";
    Logger logger = Logger.getLogger(NewsCommand.class);

    @Override
    public void process() throws ServletException, IOException {

        logger.info("NewsCommand begin");
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
