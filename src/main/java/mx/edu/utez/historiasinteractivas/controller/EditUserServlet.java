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

@WebServlet(name = "EditUserServlet", value = "/editUser")
@MultipartConfig
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String user_name = request.getParameter("user_name");
        String name = request.getParameter("name");
        String paternalName = request.getParameter("paternalName");
        String maternalName = request.getParameter("maternalName");
        Part filePart = request.getPart("userPicture");
        HttpSession session = request.getSession();

        if(email.length()>50 ||
                user_name.length() > 50 ||
                name.length() > 40 ||
                paternalName.length() > 20 ||
                maternalName.length() > 20){
            session.setAttribute("errorMessage", "Ha sobrepasado el límite de carácteres");
            response.sendRedirect("register.jsp");
            return;
        }

        UsuarioDao dao = new UsuarioDao();
        User usuario = dao.findUserByEmail(email);

        // Verificar si el usuario fue encontrado
        if (usuario == null) {
            session.setAttribute("message", "Ha ocurrido un error al actualizar los datos");
            response.sendRedirect("editUser.jsp");
            return;
        }

        // Manejo de la imagen de perfil
        if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator + "profilePictures";
            String filePath = uploadDir + File.separator + fileName;
            filePart.write(filePath);

            usuario.setProfilePicture("uploads/profilePictures/" + fileName);
        }

        // Actualización de la información del usuario
        usuario.setEmail(email);
        if (user_name == null || user_name.isEmpty()){
            usuario.setUser(email);
        } else {
            usuario.setUser(user_name);
        }
        usuario.setName(name);
        usuario.setPaternalSurname(paternalName);
        usuario.setMaternalSurname(maternalName);

        // Actualización en la base de datos
        if (dao.updateUser(usuario)) {
            session.setAttribute("user", usuario);
            session.setAttribute("message", "Datos actualizados correctamente");
            response.sendRedirect("editUser.jsp");
        } else {
            session.setAttribute("message", "Ha ocurrido un error al actualizar los datos");
            response.sendRedirect("editUser.jsp");
        }
    }

}