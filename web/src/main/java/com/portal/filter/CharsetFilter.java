package com.portal.filter;

import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Implementation of the Filter interface for the task, changing coding focusing
 * of the request and response to the encoding specified filter parameter.
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = {"/*"}, initParams =
        {@WebInitParam(name= "encoding", value = "UTF-8", description = "Encoding Param")})

    public class CharsetFilter implements Filter {

    private String code;

    private static Logger logger = Logger.getLogger(CharsetFilter.class);

    public void destroy() {
        code = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String codeRequest = req.getCharacterEncoding();
// setting the encoding parameters of the filter, if not set
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            req.setCharacterEncoding(code);
            resp.setCharacterEncoding(code);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        code = config.getInitParameter("encoding");
    }

}
