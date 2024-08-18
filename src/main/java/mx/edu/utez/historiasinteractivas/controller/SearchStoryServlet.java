package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.historiasinteractivas.dao.StoryDao;
import mx.edu.utez.historiasinteractivas.model.Story;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="SearchStoryServlet", value = "/searchStory")
public class SearchStoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_story = req.getParameter("id_story");
        if (id_story == null) {
            req.setAttribute("message", "No se ingresó un código");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
            return;
        }

        Story story = null;
        StoryDao storyDao = new StoryDao();

        try {
            story = storyDao.findPublicStoryByCode(id_story);
            if (story == null) {
                req.setAttribute("message", "La historia que buscas no existe");
                req.getRequestDispatcher("index.jsp").forward(req,resp);
                return;
            }

            // Validar si la historia es restringida o un borrador
            int storyType = story.getStory_type();
            if (storyType == 2 || storyType == 3) {
                req.setAttribute("message", "La historia está restringida o es un borrador y no puede ser accedida.");
                req.getRequestDispatcher("index.jsp").forward(req,resp);
                return;
            }

            // Aquí rediriges con el método GET pasando los datos en la URL
            resp.sendRedirect("viewStory?id_story=" + id_story);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
