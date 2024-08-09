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
        int usuariosPorPagina = 10;

        int paginaActual = 1;
        String pagina = request.getParameter("page");
        if (pagina != null) {
            paginaActual = Integer.parseInt(pagina);
        }

        List<User> todosLosUsuarios = usuarioDao.getAllUsers();

        int startIndex = (paginaActual - 1) * usuariosPorPagina;
        int endIndex = Math.min(startIndex + usuariosPorPagina, todosLosUsuarios.size());

        List<User> usuariosPaginados = todosLosUsuarios.subList(startIndex, endIndex);

        HttpSession session = request.getSession();
        session.setAttribute("usuarios", usuariosPaginados);
        session.setAttribute("totalPaginas", (int) Math.ceil((double) todosLosUsuarios.size() / usuariosPorPagina));
        session.setAttribute("paginaActual", paginaActual);

        request.getRequestDispatcher("/adminUsers.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        int usuariosPorPagina = 10;
        int paginaActual = 1;
        String pagina = request.getParameter("page");
        if (pagina != null) {
            paginaActual = Integer.parseInt(pagina);
        }

        if ("buscar".equals(action)) {
            String email = request.getParameter("email");
            ArrayList<User> usuarios = usuarioDao.findAllUsersByEmail(email);

            if (!usuarios.isEmpty()) {
                session.setAttribute("usuarios", usuarios);
            } else {
                request.setAttribute("message", "No existen usuarios que cumplan las condiciones");
                usuarios = usuarioDao.getAllUsers();
                session.setAttribute("usuarios", usuarios);
            }

        } else if ("deshabilitar".equals(action)) {
            String email = request.getParameter("email");
            boolean resultado = usuarioDao.disableUserByEmail(email);
            request.setAttribute("message", resultado ? "Usuario deshabilitado correctamente." : "Usuario no encontrado.");
            ArrayList<User> usuarios = usuarioDao.getAllUsers();
            session.setAttribute("usuarios", usuarios);

        } else if ("habilitar".equals(action)) {
            String email = request.getParameter("email");
            boolean resultado = usuarioDao.enableUserByEmail(email);
            request.setAttribute("message", resultado ? "Usuario habilitado correctamente." : "Usuario no encontrado.");
            ArrayList<User> usuarios = usuarioDao.getAllUsers();
            session.setAttribute("usuarios", usuarios);
        }

        List<User> todosLosUsuarios = (List<User>) session.getAttribute("usuarios");
        int startIndex = (paginaActual - 1) * usuariosPorPagina;
        int endIndex = Math.min(startIndex + usuariosPorPagina, todosLosUsuarios.size());
        List<User> usuariosPaginados = todosLosUsuarios.subList(startIndex, endIndex);

        session.setAttribute("usuarios", usuariosPaginados);
        session.setAttribute("totalPaginas", (int) Math.ceil((double) todosLosUsuarios.size() / usuariosPorPagina));
        session.setAttribute("paginaActual", paginaActual);

        request.getRequestDispatcher("/adminUsers.jsp").forward(request, response);
    }
}





