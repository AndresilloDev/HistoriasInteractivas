package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;
import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.utils.GmailSender;
import mx.edu.utez.historiasinteractivas.utils.RandomStringGenerator;

import java.io.IOException;

@WebServlet(name="RecoverPasswordServlet", value="/recoverPassword")
public class RecoverPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correo = req.getParameter("email");

        UsuarioDao dao = new UsuarioDao();
        User user = new User(correo);

        if (!dao.existsUser(user)) {
            System.out.println("No usuario correspondiente");
            req.setAttribute("errorMessage", "No existe el usuario " + correo);
            req.getRequestDispatcher("recoverPassword.jsp").forward(req, resp);
        } else {
            //Generar el token aleatorio y crear un link en base a eso
            String token = RandomStringGenerator.generateRandomString();
            String resetLink = "http://localhost:8080/Historias_Interactivas_war_exploded/changePassword?token=" + token + "&email="+correo;

            //Guardar el token en la base de datos para su posterior verificado
            if(!dao.saveChangePasswordToken(user, token)){
                System.out.println("No se pudo almacenar");
                req.setAttribute("errorMessage", "Error al cambiar la contraseña, inténtelo de nuevo en unos momentos" + correo);
                req.getRequestDispatcher("recoverPassword.jsp").forward(req, resp);
            }
            try {
                GmailSender gs = new GmailSender();
                gs.sendEmailRecoverPassword(user, resetLink);
                System.out.println("Correo enviado");
                req.setAttribute("message", "Favor de revisar su correo para cambiar la contraseña.");
                req.getRequestDispatcher("recoverPassword.jsp").forward(req, resp);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void init() throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
