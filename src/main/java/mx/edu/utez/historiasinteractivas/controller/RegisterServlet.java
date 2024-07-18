package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;
import mx.edu.utez.historiasinteractivas.model.User;


import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener parámetros del form
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirmPassword");

        UsuarioDao usuarioDao = new UsuarioDao();
        User usuario = new User(password, email);

        if (usuarioDao.existsUser(usuario)) {
            System.out.println("usuario ya registrado");
            req.setAttribute("errorMessage", "El usuario ya está registrado");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return; // Salir del método después de reenviar
        }

        if (!password.equals(confirm_password)) {
            System.out.println("bruh");
            req.setAttribute("errorMessage", "¡Las contraseñas no coinciden!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else {
            try{
                if (usuarioDao.insert(usuario)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", usuario); // Guardar usuario en la sesión
                    resp.sendRedirect("index.jsp");
                } else {
                    req.setAttribute("errorMessage", "¡Error al registrar el usuario!");
                    req.getRequestDispatcher("register.jsp").forward(req, resp);
                }
            } catch (Exception e){
                req.setAttribute("errorMessage", "Ha ocurrido un error, vuelva a intentarlo más tarde");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        }
    }
}