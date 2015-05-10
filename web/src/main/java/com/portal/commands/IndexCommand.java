package com.portal.commands;

import beans.New;
import com.portal.BlManager;
import com.portal.FrontCommand;
import exeption.ModelException;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;
import java.util.List;


public class IndexCommand extends FrontCommand {

    public static String NAME = "Index";

    @Override
    public void process() throws ServletException, IOException {
        Logger logger = Logger.getLogger(IndexCommand.class);
        HttpSession session = request.getSession();
        New newsitem = null;
        List newslist;
        String item_id = request.getParameter("item_id");

        try {
            if (item_id == null) {
                item_id = "1";
            }
            newsitem = BlManager.getNewsManager().getByKey(Integer.valueOf(item_id));
            newslist = BlManager.getNewsManager().getAll();
            session.setAttribute("newsitem", newsitem);
            session.setAttribute("newslist", newslist);
            session.setAttribute("item_id", item_id);

        } catch (ModelException e) {
            logger.info(e);
        }
        try {
            forward(Paths.INDEX);
        } catch (ServletException e) {
            logger.info("Error:" + e);
        } catch (IOException e) {
            logger.info("Error:" + e);
        }
    }
}