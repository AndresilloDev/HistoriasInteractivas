package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {
        "/editUser.jsp",
        "/adminUsers.jsp",
        "/editUser.jsp",
})
public class FilterUserServlet implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Control de caché
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        httpResponse.setDateHeader("Expires", 0); // Proxies.
        HttpSession session = httpRequest.getSession(false);

        if(session != null) {
            chain.doFilter(request,response);
        } else {
            httpResponse.sendRedirect("login.jsp");
        }
    }
}
