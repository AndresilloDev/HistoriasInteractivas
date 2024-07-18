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

        //Obtener los parámetros del formulario
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        User usuario = new User(user, password);

        //Se obtiene el usuario si existe
        UsuarioDao dao = new UsuarioDao();
        User u = dao.getUser(usuario);

        if (u != null && u.isStatus()) { // El usuario existe y está activo
            System.out.println("Pasado la primera prueba");
            System.out.println(u.getUser_type());

            // Guardar usuario en la sesión
            HttpSession session = req.getSession();
            session.setAttribute("user", u);

            // El usuario es un creador de historias
            if (u.getUser_type() == 0) {
                System.out.println("userNomra");
                resp.sendRedirect("index.jsp");

            // El usuario es un administrador
            } else if (u.getUser_type() == 1) {
                System.out.println("Admin");
                resp.sendRedirect("paginaNoCreadaParaAdministrador.jsp");
            }

        } else { // El usuario no existe o las credenciales son incorrectas
            System.out.println("ohno");
            resp.sendRedirect("login.jsp");
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
