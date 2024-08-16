package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.historiasinteractivas.dao.StoryDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateStoryStatusServlet", value = "/updateStoryStatus")
public class UpdateStoryStatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener parámetros de la solicitud
        String id_story = req.getParameter("id_story");
        String action = req.getParameter("action");

        // Determinar el nuevo estado de la historia
        int newStatus = 3; // Por defecto, establecer como borrador

        if ("publicar".equals(action)) {
            newStatus = 1; // 1 = Historia Publicada
        } else if ("restringir".equals(action)) {
            newStatus = 2; // 2 = Historia Restringida
        }

        StoryDao storyDao = new StoryDao();

        try {
            // Actualizar el estado de la historia en la base de datos
            if (storyDao.updateStoryStatus(id_story, newStatus)) {
                req.setAttribute("message", "Estado de la historia actualizado correctamente.");
            } else {
                req.setAttribute("errorMessage", "No se pudo actualizar el estado de la historia.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
