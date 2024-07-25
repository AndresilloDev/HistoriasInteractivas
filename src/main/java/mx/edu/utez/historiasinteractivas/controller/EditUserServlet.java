package mx.edu.utez.historiasinteractivas.controller;

import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // Ver todos los parámetros recibidos para depuración
        request.getParameterMap().forEach((key, value) -> {
            System.out.println("Parameter Name: " + key + ", Value: " + String.join(", ", value));
        });

        String email = request.getParameter("email");
        String user = request.getParameter("user");
        String name = request.getParameter("name");
        String paternalName = request.getParameter("paternalName");
        String maternalName = request.getParameter("maternalName");

        // Imprimir valores para depuración
        System.out.println("Email: " + email);
        System.out.println("User: " + user);
        System.out.println("Name: " + name);
        System.out.println("Paternal Name: " + paternalName);
        System.out.println("Maternal Name: " + maternalName);

        if (email == null || user == null || name == null || paternalName == null || maternalName == null) {
            System.out.println("Algunos parámetros son nulos. Verificar el formulario y la solicitud.");
            response.sendRedirect("profile.jsp?updateSuccess=false");
            return;
        }

        User updatedUser = new User();
        updatedUser.setEmail(email);
        updatedUser.setUser(user);
        updatedUser.setName(name);
        updatedUser.setPaternalSurname(paternalName);
        updatedUser.setMaternalSurname(maternalName);

        UsuarioDao usuarioDao = new UsuarioDao();
        boolean isUpdated = usuarioDao.updateUser(updatedUser);

        if (isUpdated) {
            response.sendRedirect("profile.jsp?updateSuccess=true");
        } else {
            response.sendRedirect("profile.jsp?updateSuccess=false");
        }
    }
}
