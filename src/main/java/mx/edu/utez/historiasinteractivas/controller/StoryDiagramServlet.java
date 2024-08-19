package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mx.edu.utez.historiasinteractivas.dao.StoryDao;
import mx.edu.utez.historiasinteractivas.model.Story;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "StoryDiagramServlet", value = "/storyDiagram")
public class StoryDiagramServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
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

            String idStory = jsonObject.getString("idStory");
            String title = jsonObject.getString("title");
            String diagram = jsonObject.getString("diagram");

            Story story = new Story(idStory, title, diagram);
            StoryDao dao = new StoryDao();

            if (!dao.updateStory(story)) { // Actualizar historia existente
                request.setAttribute("errorMessage", "¡Error al actualizar la historia!");
            } else {
                request.setAttribute("message", "¡Historia actualizada correctamente!");
            }

            // Enviar una respuesta JSON al cliente
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");
            response.getWriter().write(jsonResponse.toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Se produjo un error interno en el servidor.");
            response.getWriter().write(jsonResponse.toString());
            e.printStackTrace();  // Para ver el error en los logs del servidor
        }
    }
}