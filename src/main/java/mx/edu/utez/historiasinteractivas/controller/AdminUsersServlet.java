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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="AdminUsersServlet", value = "/adminUsers")
public class AdminUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Funciono bien hasta aquí");
        List<User> usuarios = usuarioDao.getAllUsers();
        HttpSession session = request.getSession();
        session.setAttribute("usuarios", usuarios);
        System.out.println("Funciono bien hasta aquí V2");
        request.getRequestDispatcher("/adminUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if ("buscar".equals(action)) {
            String email = request.getParameter("email");
            ArrayList<User> usuarios = usuarioDao.findAllUsersByEmail(email);

            if (!usuarios.isEmpty()) {
                session.setAttribute("usuarios", usuarios);
                request.getRequestDispatcher("/adminUsers.jsp").forward(request, response);

            } else {
                request.setAttribute("message", "No existen usuarios que cumplan las condiciones");


                usuarios = usuarioDao.getAllUsers();
                session.setAttribute("usuarios", usuarios);
                request.getRequestDispatcher("/adminUsers.jsp").forward(request, response);
            }

        } else if ("deshabilitar".equals(action)) {
            String email = request.getParameter("email");
            boolean resultado = usuarioDao.disableUserByEmail(email);

            request.setAttribute("message", resultado ? "Usuario deshabilitado correctamente." : "Usuario no encontrado.");

            ArrayList<User> usuarios = usuarioDao.getAllUsers();
            session.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/adminUsers.jsp").forward(request, response);

        } else if ("habilitar".equals(action)) {
            String email = request.getParameter("email");
            boolean resultado = usuarioDao.enableUserByEmail(email);

            request.setAttribute("message", resultado ? "Usuario habilitado correctamente." : "Usuario no encontrado.");

            ArrayList<User> usuarios = usuarioDao.getAllUsers();
            session.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/adminUsers.jsp").forward(request, response);
        }

    }
}





