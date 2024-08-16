package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;
import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.utils.GmailSender;

import java.io.IOException;
import java.security.SecureRandom;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        // Verificar que las contraseñas coincidan
        if (!password.equals(confirmPassword)) {
            req.setAttribute("errorMessage", "¡Las contraseñas no coinciden!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        if(email.length()>50 || password.length() > 256){
            req.setAttribute("errorMessage", "Ha sobrepasado el límite de carácteres");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        // Crear el usuario
        UsuarioDao dao = new UsuarioDao();
        if (!dao.existsUser(email, password)){
            // Generar el código de verificación y enviarlo por correo electrónico
            try {
                User usuario = new User(email, password);
                String verificationCode = generateVerificationCode();
                GmailSender verification = new GmailSender();
                verification.sendVerificationEmail(email, verificationCode);

                // Guardar el usuario y el código de verificación en la sesión
                HttpSession session = req.getSession();
                session.setAttribute("registerUser", usuario);
                session.setAttribute("verificationCode", verificationCode);

                resp.sendRedirect("verifyAccount.jsp");
            } catch (Exception e) {
                req.setAttribute("errorMessage", "¡Error al enviar el correo de verificación!");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        } else {
            try {
                req.setAttribute("errorMessage", "¡El usuario ya existe!");
                resp.sendRedirect("register.jsp");

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(1000000);
        return String.format("%06d", num);
    }
}
