package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;
import mx.edu.utez.historiasinteractivas.model.User;

import java.io.IOException;

@WebServlet(name="ChangePasswordServlet", value = "/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String tokenGiven = req.getParameter("token");

        req.setAttribute("email", email);
        req.setAttribute("token", tokenGiven);
        req.getRequestDispatcher("changePassword.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String tokenGiven = req.getParameter("token");
        String newPassword = req.getParameter("newPassword");

        UsuarioDao dao = new UsuarioDao();
        User user = new User(email);

        String token = dao.getPasswordToken(user);

        if (tokenGiven.equals(token)) {
            dao.updatePassword(user, newPassword);
            req.setAttribute("message", "Contraseña actualizada correctamente.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            // Redirige a la página de inicio de sesión
        } else {
            req.setAttribute("errorMessage", "Token no válido.");
            req.getRequestDispatcher("changePassword.jsp").forward(req, resp);
            // Vuelve a la página de cambio de contraseña
        }
    }
}
