package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import mx.edu.utez.historiasinteractivas.dao.StoryDao;
import mx.edu.utez.historiasinteractivas.model.Story;
import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.utils.RandomStringGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(name = "CreateStoryServlet", value = "/createStory")
@MultipartConfig
public class CreateStoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Part filePart = request.getPart("coverImage");
        String storyThumbnail = null;

        if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator + "storiesThumbnails";
            String filePath = uploadDir + File.separator + fileName;
            filePart.write(filePath);

            storyThumbnail = ("uploads/storiesThumbnails/" + fileName);
        }else {
            storyThumbnail = ("resources/img/thumbnailDefault.png");
        }

        String id_story = RandomStringGenerator.generateRandomString(6);
        id_story= id_story.toUpperCase();
        String email = user.getEmail();
        String storyTitle = request.getParameter("storyTitle");
        String storyDescription = request.getParameter("storyDescription");
        String json = "{ \"class_name\": \"GraphLinksModel\",\n\"nodeDataArray\": [{\"category\":\"startEvent\",\"key\":-1,\"loc\":\"0 0\"}],\n\"linkDataArray\": []}";

        StoryDao dao = new StoryDao();
        Story story = new Story(id_story, email, storyTitle, storyDescription, storyThumbnail, json);

        if (dao.insertStory(story)) {
            request.setAttribute("message", "¡Historia creada correctamente!");
            response.sendRedirect("createStory.jsp?id_story="+id_story);
        } else {
            request.setAttribute("errorMessage", "¡Error al crear la historia!");
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}