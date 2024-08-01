package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.http.*;
import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/editUser")
@MultipartConfig
public class EditUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        UsuarioDao dao = new UsuarioDao();
        User userData = dao.findUserByEmail(email);

            request.setAttribute("user", userData.getUser());
            request.setAttribute("name", userData.getName());
            request.setAttribute("paternalName", userData.getPaternalSurname());
            request.setAttribute("maternalName", userData.getMaternalSurname());
            request.getRequestDispatcher("editUser.jsp").forward(request, response);


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String user = request.getParameter("user");
        String name = request.getParameter("name");
        String paternalName = request.getParameter("paternalName");
        String maternalName = request.getParameter("maternalName");
        Part filePart = request.getPart("userPicture");

        UsuarioDao dao = new UsuarioDao();
        User usuario = dao.findUserByEmail(email);

        String profilePicture = usuario.getProfilePicture();

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

        if (dao.findUserByEmail(email) != null) {
            User updatedUser = new User();
            updatedUser.setEmail(email);
            updatedUser.setUser(user);
            updatedUser.setName(name);
            updatedUser.setPaternalSurname(paternalName);
            updatedUser.setMaternalSurname(maternalName);

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