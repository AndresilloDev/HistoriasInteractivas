package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.historiasinteractivas.dao.StoryDao;
import mx.edu.utez.historiasinteractivas.model.Event;
import mx.edu.utez.historiasinteractivas.model.Story;
import mx.edu.utez.historiasinteractivas.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="ViewStoryServlet", value = "/viewStory")
public class ViewStoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String id_story = req.getParameter("id_story");
        Story story = (Story) session.getAttribute("story");
        int event_id;

        try{
            event_id = Integer.parseInt(req.getParameter("event_id"));
        } catch (NumberFormatException e){
            event_id = -1;
        }

        StoryDao storyDao = new StoryDao();

        //Si no existe ninguna historia con el código dado
        if (!storyDao.isPublicCodeUnique(id_story)) {
            req.setAttribute("message", "No existe la historia");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

        //Si la historia actual no es igual a la almacenada
        if(story == null || !story.getId_story().equals(id_story)){
            try {
                story = storyDao.findPublicStoryByCode(id_story);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            session.setAttribute("story", story);
        }

        System.out.println(story.getModel());
        System.out.println(story + " story GET");
        story.getModel().toString();

        Event event = story.getModel().getEventByKey(event_id);

        System.out.println("Evento: " + event.toString());

        lol(req, story, event_id, event);

        System.out.println("Id historia: " + id_story);
        System.out.println("Id escena" + event_id);
        System.out.println("Usuario: " + session.getAttribute("user").toString());

        req.getRequestDispatcher("viewStory.jsp").forward(req, resp);
    }

    static void lol(HttpServletRequest req, Story story, int event_id, Event event) {
        req.setAttribute("text", event.getText());
        req.setAttribute("description", event.getDescription());
        req.setAttribute("image", event.getImage());
        req.setAttribute("link", event.getLink());
        req.setAttribute("video", event.getVideo());
        req.setAttribute("audio", event.getAudio());

        ArrayList<String> linkedTexts = story.getModel().getTextOfLinkedEvents(event_id);
        if (linkedTexts != null) {
            req.setAttribute("option1", !linkedTexts.isEmpty() ? linkedTexts.get(0) : null);
            req.setAttribute("option2", linkedTexts.size() > 1 ? linkedTexts.get(1) : null);
        } else {
            req.setAttribute("option1", null);
            req.setAttribute("option2", null);
        }
    }
}
