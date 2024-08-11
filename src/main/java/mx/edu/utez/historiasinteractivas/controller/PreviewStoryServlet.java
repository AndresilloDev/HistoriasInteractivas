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
import mx.edu.utez.historiasinteractivas.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="PreviewStoryServlet", value = "/previewStory")
public class PreviewStoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String id_story = req.getParameter("id_story");
        StoryDao storyDao = new StoryDao();
        try {
            Story story = storyDao.findByCode(id_story, user);
            story.setScenes(story.parseJsonToScenes());

            System.out.println(story.getJson());
            System.out.println(story + " story GET");
            System.out.println(story.getScenes());

            Scene scene = story.getScene(1);
            System.out.println(scene + " escena GET");
            System.out.println(scene.toString());

            req.setAttribute("scene", scene);
            req.setAttribute("story", story);
            req.setAttribute("image", scene.getScene_image());
            req.setAttribute("link", scene.getScene_link());
            req.setAttribute("video", scene.getScene_video());
            req.setAttribute("audio", scene.getScene_audio());
            req.getRequestDispatcher("/previewStory.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Story story = (Story) session.getAttribute("story");
        System.out.println( "historias en servlet " + story.toString());
        String option = req.getParameter("option");
        Scene lastScene = (Scene) req.getAttribute("scene");
        Scene newScene = new Scene();

        if(option.equals("option1")){
            newScene = story.getScene(lastScene.getDestiny_scene_option_1());

        } else if (option.equals("option2")) {
            newScene = story.getScene(lastScene.getDestiny_scene_option_2());

        }

        System.out.println(newScene.toString());

        req.setAttribute("scene", newScene);
        req.setAttribute("image", newScene.getScene_image());
        req.setAttribute("link", newScene.getScene_link());
        req.setAttribute("video", newScene.getScene_video());
        req.setAttribute("audio", newScene.getScene_audio());
        req.getRequestDispatcher("/previewStory.jsp").forward(req, resp);


    }
}
