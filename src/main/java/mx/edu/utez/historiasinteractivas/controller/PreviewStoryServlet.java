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

@WebServlet(name="PreviewStoryServlet", value = "/previewStory")
public class PreviewStoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtener el usuario
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        //Obtener la historia de la sesión y el id de los parámetros
        // -- En caso de no haber parámetro con id, se coloca el de la sesión --
        Story story = (Story) session.getAttribute("story");
        String id_story = req.getParameter("id_story");
        if(id_story==null) {
            id_story = (String) session.getAttribute("id_story");
        }

        StoryDao storyDao = new StoryDao();

        //Obtener el evento actual
        // Si no hay evento, colocar el primero
        int event_id;
        try{
            event_id = Integer.parseInt(req.getParameter("event_id"));
        } catch (NumberFormatException e){
            event_id = -1;
        }

        try {
            //Si no existe ninguna historia con el código dado
            if (storyDao.isCodeUnique(id_story)) {
                req.setAttribute("message", "No existe la historia");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }

            //Si la historia actual no es igual a la almacenada, obtenerla según el link y actualizarla en la sesión
                story = storyDao.findByCode(id_story, user);
                session.removeAttribute("story");
                session.setAttribute("story", story);

            //Obtener los datos del evento actual
            Event event = story.getModel().getEventByKey(event_id);

            //Colocar los datos en el request
            req.setAttribute("id_story", id_story);
            req.setAttribute("event_id", event_id);
            req.setAttribute("text", event.getText());
            req.setAttribute("description", event.getDescription());
            req.setAttribute("image", event.getImage());
            req.setAttribute("link", event.getLink());
            req.setAttribute("video", event.getVideo());
            req.setAttribute("audio", event.getAudio());

            //Obtener el texto de las opciones
            ArrayList<String> linkedTexts = story.getModel().getTextOfLinkedEvents(event_id);
            if (linkedTexts != null) {
                //En caso de que solo exista un link, colocar ese texto, y en option2 colocar null
                //Por la validación anterior, debe haber al menos un texto de forma asegurada
                req.setAttribute("option1", linkedTexts.get(0));
                req.setAttribute("option2", linkedTexts.size() > 1 ? linkedTexts.get(1) : null);
            } else {
                req.setAttribute("option1", null);
                req.setAttribute("option2", null);
            }

            //En caso de haber una opción en los parámetros (Indica que se llamó desde la escena con un botón), tomarla y mandarla a la página
            String option = req.getParameter("option");

            if(option != null && option.equals("option1")){
                resp.sendRedirect("previewStory?id_story="+id_story+"&event_id="+story.getModel().getKeysOfLinkedEvents(event_id).get(0));
                return;
            } else if (option != null && option.equals("option2")) {
                resp.sendRedirect("previewStory?id_story=" + id_story + "&event_id=" + story.getModel().getKeysOfLinkedEvents(event_id).get(1));
                return;
            }

            req.getRequestDispatcher("previewStory.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
