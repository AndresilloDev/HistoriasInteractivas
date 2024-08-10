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

@WebServlet(name="LoginServlet", value="/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Obtener los parámetros del formulario
        String emailOrUser = req.getParameter("user");
        String password = req.getParameter("password");

        // Se obtiene el usuario si existe
        UsuarioDao dao = new UsuarioDao();
        User usuario = dao.getUser(emailOrUser, password);

        if (usuario == null) { // El usuario no existe
            req.setAttribute("errorMessage", "¡El usuario o la contraseña son incorrectos!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        if (!usuario.isStatus()) { // El usuario no está activo
            String adminEmail = dao.getAdminEmail();
            req.setAttribute("errorMessage",
                    "El usuario está deshabilitado." +
                       "<br>" +
                       "Por favor, contacta a un administrador para más información: " + adminEmail);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        // Guardar usuario en la sesión
        HttpSession session = req.getSession();
        session.setAttribute("user", usuario);
        resp.sendRedirect("index.jsp");
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
