package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.historiasinteractivas.dao.StoryDao;
import mx.edu.utez.historiasinteractivas.model.Scene;
import mx.edu.utez.historiasinteractivas.model.Story;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="PreviewStoryServlet", value = "/previewStory")
public class PreviewStoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_story = req.getParameter("id_story");
        StoryDao storyDao = new StoryDao();
        try {
            Story story = storyDao.findByCode(id_story);
            story.setScenes(story.parseJsonToScenes());

            System.out.println(story.getJson());
            System.out.println(story + " story GET");
            System.out.println(story.getScenes());

            Scene scene = story.getScene(1);
            System.out.println(scene + " escena GET");
            System.out.println(scene.toString());

            HttpSession session = req.getSession();
            req.setAttribute("scene", scene);
            session.setAttribute("story", story);
            req.getRequestDispatcher("/previewStory.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        
        if(option.equals("option1")){
            
        } else if (option.equals("option2")) {

        }
    }
}
