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

@WebServlet(name="LoginServlet", value="/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int code = (int) (Math.random() * 999999);

        // Obtener parámetros del form
        String email = req.getParameter("email");
        String password  = req.getParameter("password");


        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = new Usuario(email, password);

        if (usuarioDao.existsUser(usuario)){ //La función verifica si existe el usuario
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("errorMessage", "No existe la cuenta");
        }

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
