package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.historiasinteractivas.dao.StoryDao;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "UpdateStoryStatusServlet", value = "/updateStoryStatus")
public class UpdateStoryStatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener parámetros de la solicitud
        String id_story = req.getParameter("id_story");
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        java.sql.Date release_date = null;

        // Determinar el nuevo estado de la historia
        int newStatus = 3; // Por defecto, establecer como borrador

        if ("publicar".equals(action)) {
            newStatus = 1; // 1 = Historia Publicada
            release_date = new java.sql.Date(new java.util.Date().getTime());  // Fecha actual en formato java.sql.Date
        } else if ("restringir".equals(action)) {
            newStatus = 2; // 2 = Historia Restringida
            release_date = null;  // Enviar null para la fecha
        }

        StoryDao storyDao = new StoryDao();

        try {
            if (storyDao.updateStoryStatus(id_story, newStatus, release_date)) {
                session.setAttribute("message", "Estado de la historia actualizado correctamente.");
            } else {
                session.setAttribute("errorMessage", "No se pudo actualizar el estado de la historia.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
