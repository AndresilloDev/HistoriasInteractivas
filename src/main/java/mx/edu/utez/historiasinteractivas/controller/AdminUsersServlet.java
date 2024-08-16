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

        List<User> todosLosUsuarios = usuarioDao.getAllNotAdmin();

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
        String email = request.getParameter("email");
        HttpSession session = request.getSession();

        int usuariosPorPagina = 10;
        int paginaActual = 1;
        String pagina = request.getParameter("page");
        if (pagina != null) {
            paginaActual = Integer.parseInt(pagina);
        }

        switch (action) {
            case "buscar":
                String emailToSearch = request.getParameter("email");
                ArrayList<User> usuarios = usuarioDao.findAllUsersByEmail(emailToSearch);
                if (!usuarios.isEmpty()) {
                    int startIndex = (paginaActual - 1) * usuariosPorPagina;
                    int endIndex = Math.min(startIndex + usuariosPorPagina, usuarios.size());
                    List<User> usuariosPaginados = usuarios.subList(startIndex, endIndex);
                    session.setAttribute("usuarios", usuariosPaginados);
                    request.getRequestDispatcher("/adminUsers.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "No existen usuarios que cumplan las condiciones.");
                    List<User> todosLosUsuarios = usuarioDao.getAllNotAdmin();
                    session.setAttribute("usuarios", todosLosUsuarios);
                }
                break;

            case "deshabilitar":
                User userToDisable = usuarioDao.findUserByEmail(email);

                if (userToDisable != null) {
                    if (userToDisable.isAdmin()) {
                        request.setAttribute("message", "No puedes desabilitar un administrador.");
                    } else {
                        boolean resultado = usuarioDao.disableUserByEmail(email);
                        request.setAttribute("message", resultado ? "Usuario deshabilitado correctamente." : "Error al deshabilitar al usuario.");
                    }
                } else {
                    request.setAttribute("message", "Usuario no encontrado.");
                }
                break;

            case "habilitar":
                User userToEnable = usuarioDao.findUserByEmail(email);

                if (userToEnable != null) {
                    boolean resultado = usuarioDao.enableUserByEmail(email);
                    request.setAttribute("message", resultado ? "Usuario habilitado correctamente." : "Error al habilitar al usuario.");

                } else {
                    request.setAttribute("message", "Usuario no encontrado.");
                }
                break;
        }

        List<User> todosLosUsuarios = usuarioDao.getAllNotAdmin();
        int startIndex = (paginaActual - 1) * usuariosPorPagina;
        int endIndex = Math.min(startIndex + usuariosPorPagina, todosLosUsuarios.size());
        List<User> usuariosPaginados = todosLosUsuarios.subList(startIndex, endIndex);

        session.setAttribute("usuarios", usuariosPaginados);
        session.setAttribute("totalPaginas", (int) Math.ceil((double) todosLosUsuarios.size() / usuariosPorPagina));
        session.setAttribute("paginaActual", paginaActual);

        request.getRequestDispatcher("/adminUsers.jsp").forward(request, response);
    }
}





