package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;
import mx.edu.utez.historiasinteractivas.model.User;
import jakarta.servlet.http.Cookie;

import java.io.IOException;

@WebServlet(name="LoginServlet", value="/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String emailOrUser = req.getParameter("user");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        if(emailOrUser.length()>50 || password.length() > 256){
            session.setAttribute("errorMessage", "Ha sobrepasado el límite de carácteres");
            resp.sendRedirect("register.jsp");
            return;
        }

        // Se obtiene el usuario si existe
        UsuarioDao dao = new UsuarioDao();
        User usuario = dao.getUser(emailOrUser, password);

        if (usuario == null) { // El usuario no existe
            session.setAttribute("errorMessage", "¡El usuario o la contraseña son incorrectos!");
            resp.sendRedirect("login.jsp");
            return;
        }

        if (!usuario.isStatus()) { // El usuario no está activo
            session.setAttribute("errorMessage",
                    "El usuario está deshabilitado." +
                       "<br>" +
                       "Por favor, contactanos para más información: historiasinteractivasutez@gmail.com");
            resp.sendRedirect("login.jsp");
            return;
        }

        // Guardar usuario en la sesión
        session = req.getSession();
        session.setAttribute("user", usuario);

        // Dentro de tu servlet o filtro
        Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
        sessionCookie.setHttpOnly(true);
        sessionCookie.setMaxAge(60 * 60); // Duración de la cookie en segundos
        sessionCookie.setPath("/");

        // Agregar SameSite al header Set-Cookie
        resp.addHeader("Set-Cookie", sessionCookie.getName() + "=" + sessionCookie.getValue() + "; HttpOnly; SameSite=None; Secure; Path=/");
        resp.addCookie(sessionCookie);
        resp.sendRedirect("index.jsp");
    }
}
