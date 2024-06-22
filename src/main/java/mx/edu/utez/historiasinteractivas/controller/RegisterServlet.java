package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;
import mx.edu.utez.historiasinteractivas.model.Usuario;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener parámetros del form
        String email = req.getParameter("email");
        String token = req.getParameter("token");
        String name = req.getParameter("name");
        String first_last_name = req.getParameter("first_last_name");
        String last_last_name = req.getParameter("last_last_name");
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm_password");

        if (!password.equals(confirm_password)) {
            req.setAttribute("errorMessage", "¡Las contraseñas no coinciden!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else {
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuario = new Usuario(email, token, name, first_last_name, last_last_name, password, user);
            if (usuarioDao.insert(usuario)) {
                resp.sendRedirect("index.jsp");
            } else {
                req.setAttribute("errorMessage", "¡Error al registrar el usuario!");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        }
    }
}
