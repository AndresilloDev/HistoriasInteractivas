package mx.edu.utez.historiasinteractivas.controller;

import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/EditUserServlet")
@MultipartConfig
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String user = request.getParameter("user");
        String name = request.getParameter("name");
        String paternalName = request.getParameter("paternalName");
        String maternalName = request.getParameter("maternalName");

        Part filePart = request.getPart("userPicture");
        String profilePicture = null;

        if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }
            String filePath = uploadDir + File.separator + fileName;
            filePart.write(filePath);
            System.out.println("Archivo guardado en: " + filePath);
            profilePicture = "uploads/" + fileName;
        }

        UsuarioDao usuarioDao = new UsuarioDao();
        User existingUser = usuarioDao.findUserByEmail(email);

        if (existingUser == null) {
            response.sendRedirect("profile.jsp?updateSuccess=false");
            return;
        }

        User updatedUser = new User();
        updatedUser.setEmail(email);
        updatedUser.setUser(user);
        updatedUser.setName(name);
        updatedUser.setPaternalSurname(paternalName);
        updatedUser.setMaternalSurname(maternalName);

        if (profilePicture != null) {
            updatedUser.setProfilePicture(profilePicture);
        } else {
            updatedUser.setProfilePicture(existingUser.getProfilePicture());
        }

        boolean isUpdated = usuarioDao.updateUser(updatedUser);

        if (isUpdated) {
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
