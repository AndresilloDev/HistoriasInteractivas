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
        if(id_story == null) {
            req.setAttribute("message", "No se ingresó un código");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }
        Story story = null;
        StoryDao storyDao = new StoryDao();

        try {
            story = storyDao.findPublicStoryByCode(id_story);
            if (story == null) {
                req.setAttribute("message", "La historia que buscas no existe");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                return;
            }

            req.getSession().setAttribute("story", story);
            req.getRequestDispatcher("viewStory.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
