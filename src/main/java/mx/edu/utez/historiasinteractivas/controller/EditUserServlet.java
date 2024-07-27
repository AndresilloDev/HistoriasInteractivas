package mx.edu.utez.historiasinteractivas.controller;

import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="EditUserServlet", value = "/editUser")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        UsuarioDao usuarioDao = new UsuarioDao();
        User user = usuarioDao.findUserByEmail(email);

        request.setAttribute("user", user);
        request.getRequestDispatcher("editUser.jsp").forward(request, response);
    }
}