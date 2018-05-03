package controlador;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author miguel
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class FilterSession implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);
            String reqURI = reqt.getRequestURI();
            System.out.println(reqURI);
            if (reqURI.startsWith("/TuTitulacion/faces/privado/") && ses.getAttribute("username") != null) {
                chain.doFilter(request, response);
            } else if (reqURI.startsWith("/TuTitulacion/faces/privado/") && ses.getAttribute("username") == null) {
                resp.sendRedirect(reqt.getContextPath() + "/faces/iniciarSesion.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {
    }
}
