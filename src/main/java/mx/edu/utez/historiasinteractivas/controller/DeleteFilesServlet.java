package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(name = "DeleteFilesServlet", value = "/deleteFiles")
@MultipartConfig
public class DeleteFilesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String baseDir = getServletContext().getRealPath("") + File.separator + "uploads";
        StringBuilder responseBuilder = new StringBuilder();

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
                    continue;
            }

            // Borrar el archivo de la carpeta correspondiente
            File fileToDelete = new File(baseDir + File.separator + subDir + File.separator + fileName);
            if (fileToDelete.exists() && fileToDelete.delete()) {
                // Añadir el nombre del archivo eliminado a la respuesta
                responseBuilder.append(fileName).append(" eliminado\n");
            } else {
                responseBuilder.append(fileName).append(" no se pudo eliminar\n");
            }
        }

        response.getWriter().write(responseBuilder.toString().trim());
    }
}
