package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;
import mx.edu.utez.historiasinteractivas.model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(name = "CreateStoryServlet", value = "/createStory")
public class CreateStoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("me estoy ejecutando");
        String email = request.getParameter("email");

        Part filePart = request.getPart("userPicture");

        UsuarioDao dao = new UsuarioDao();
        User usuario = dao.findUserByEmail(email);

        String profilePicture = usuario.getProfilePicture();

        if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator + "profilePictures";
            String filePath = uploadDir + File.separator + fileName;
            filePart.write(filePath);
            System.out.println("Archivo guardado en: " + filePath);

            profilePicture = "uploads/profilePictures/" + fileName;
        }

        if (dao.findUserByEmail(email) != null) {
            User updatedUser = new User();
            updatedUser.setEmail(email);


            if (profilePicture != null) {
                updatedUser.setProfilePicture(profilePicture);
            } else {
                updatedUser.setProfilePicture(dao.findUserByEmail(email).getProfilePicture());
            }

            if (dao.updateUser(updatedUser)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", updatedUser);
                response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("profile.jsp?updateSuccess=false");
        }



    }
}
