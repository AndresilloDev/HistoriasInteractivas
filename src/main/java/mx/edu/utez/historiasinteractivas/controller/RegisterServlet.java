package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;
import mx.edu.utez.historiasinteractivas.model.Usuario;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int code = (int) (Math.random() * 999999);

        // Obtener parámetros del form
        String user = req.getParameter("user");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int verification_code = Integer.parseInt(req.getParameter("verification_code"));
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm_password");

        // confirm_code(verification_code, resp);

        if (!password.equals(confirm_password)) {
            req.setAttribute("errorMessage", "¡Las contraseñas no coinciden!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else {
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuario = new Usuario(user, name, password, email);

            //usuarioDao.insert(usuario);

            resp.sendRedirect("index.jsp");
        }

    }

    public void confirm_code(int code, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.jsp");
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
