package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mx.edu.utez.historiasinteractivas.dao.StoryDao;
import mx.edu.utez.historiasinteractivas.model.Story;
import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.utils.RandomStringGenerator;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "CreateStoryServlet", value = "/createStory")
public class CreateStoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Leer el JSON desde la solicitud
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonString = sb.toString();

        // Convertir el string JSON a un objeto JSON
        JSONObject jsonObject = new JSONObject(jsonString);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        String tokenStory = RandomStringGenerator.generateRandomString(6);
        String email = user.getEmail();
        String idStory = request.getParameter("id_Story");
        String title = jsonObject.getString("title");
        String diagram = jsonObject.getString("diagram");

        Story story = new Story(idStory, email, title, diagram);

        StoryDao dao = new StoryDao();

        if (idStory != null && !idStory.isEmpty()) { // Actualizar historia existente
            dao.updateStory(story);

        } else {
            if (!dao.insertStory(story)) {
                request.setAttribute("errorMessage", "¡Error al registrar la historia!");
                request.getRequestDispatcher("createStory.jsp").forward(request, response);
            }
        }

        // Enviar una respuesta JSON al cliente
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "success");
        response.getWriter().write(jsonResponse.toString());
    }

}
