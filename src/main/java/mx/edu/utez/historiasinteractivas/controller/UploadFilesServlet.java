package mx.edu.utez.historiasinteractivas.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(name = "UploadFilesServlet", value = "/uploadFiles")
@MultipartConfig
public class UploadFilesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String baseDir = getServletContext().getRealPath("") + File.separator + "uploads";
        StringBuilder responseBuilder = new StringBuilder();
        String filePath = null;

        for (Part filePart : request.getParts()) {
            String fileName = filePart.getSubmittedFileName();
            if (fileName == null || fileName.isEmpty()) {
                continue;  // Si no hay archivo, continuar con el siguiente
            }

            fileName = Paths.get(fileName).getFileName().toString();

            String fileType = filePart.getContentType().split("/")[0]; // Obtener el tipo de archivo (image, audio, video)

            // Seleccionar la carpeta de destino según el tipo de archivo
            String subDir;
            switch (fileType) {
                case "image":
                    subDir = "storiesImages";
                    break;
                case "audio":
                    subDir = "storiesAudio";
                    break;
                case "video":
                    subDir = "storiesVideo";
                    break;
                default:
                    // Si el archivo no es de un tipo esperado, ignorarlo o manejarlo de otra forma
                    continue;
            }

            // Subir el archivo a la carpeta correspondiente
            File uploadDir = new File(baseDir + File.separator + subDir);
            filePath = uploadDir + File.separator + fileName;
            filePart.write(filePath);

            // Enviar la ruta relativa como respuesta para uso en JavaScript
            String relativePath = "uploads" + File.separator + subDir + File.separator + fileName;
            responseBuilder.append(relativePath).append("\n");

            System.out.println(relativePath);
            System.out.println("Información guardada correctamente");

            request.setAttribute("message", "Información guardada correctamente");
        }
        response.getWriter().write(responseBuilder.toString().trim());
    }
}