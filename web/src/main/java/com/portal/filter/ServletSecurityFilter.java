package com.portal.filter;

import beans.User;
import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Autentification filter for requests to FrontController
 */

@WebFilter(filterName = "ServletSecurityFilter", urlPatterns = { "/index" }, servletNames = { "FrontController" })

public class ServletSecurityFilter implements Filter {

    private static final Logger logger = Logger.getLogger(ServletSecurityFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String type = "GUEST";
        User user = (User) session.getAttribute("user");
        if (user == null) {
            type = "GUEST";
        }else if (user.getRole().equals("admin")) {
                type = "ADMINISTRATOR";
        }else if (user.getRole().equals("user")){
                type = "USER";
        }
        session.setAttribute("usertype", type);
        logger.info("USERTYPE - " + session.getAttribute("usertype") + "; USER - " + session.getAttribute("user"));

        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
