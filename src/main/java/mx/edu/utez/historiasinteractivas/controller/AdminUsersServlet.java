package mx.edu.utez.historiasinteractivas.controller;

import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;
import mx.edu.utez.historiasinteractivas.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminUsersServlet")
public class AdminUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> usuarios = usuarioDao.getAllUsers();
        HttpSession session = request.getSession();
        session.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/adminUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if ("buscar".equals(action)) {
            String email = request.getParameter("email");
            User usuario = usuarioDao.findUserByEmail(email);
            if (usuario != null) {
                session.setAttribute("usuario", usuario);
                System.out.println("usuario encontrado");
            } else {
                session.setAttribute("mensaje", "Usuario no encontrado.");
                System.out.println("usuario no registrado");
            }
        } else if ("deshabilitar".equals(action)) {
            String email = request.getParameter("email");
            boolean resultado = usuarioDao.disableUserByEmail(email);
            if (resultado) {
                session.setAttribute("mensaje", "Usuario deshabilitado correctamente.");
                System.out.println("asies si se deshabilito");
            } else {
                session.setAttribute("mensaje", "Usuario no encontrado.");
                System.out.println("no existe weon");
            }
        }

        response.sendRedirect(request.getContextPath() + "/adminUsers.jsp");
    }
}