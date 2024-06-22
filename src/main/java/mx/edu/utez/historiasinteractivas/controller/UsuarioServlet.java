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

@WebServlet(name="UsuarioServlet", value="/login")
public class UsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener parámetros del form
        String nombre = req.getParameter("nombre");
        String contra = req.getParameter("contra");

        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.getOne(nombre, contra);

        if (usuario == null) {
            System.out.println("Usuario incorrecto cuh");
            HttpSession session = req.getSession();
            session.setAttribute("mensaje", "Usuario incorrecto cuh");
            resp.sendRedirect("/login");
        } else {
            System.out.printf("Login válido");
            req.getSession().setAttribute("usuario", usuario);
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
