package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;
import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.utils.GenerationCode;
import mx.edu.utez.historiasinteractivas.utils.GmailSenderVerify;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        System.out.println("Datos recibidos: email=" + email + ", password=" + password);

        // Verificar que las contraseñas coincidan
        if (!password.equals(confirmPassword)) {
            System.out.println("Las contraseñas no coinciden");
            req.setAttribute("errorMessage", "¡Las contraseñas no coinciden!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        // Crear el usuario
        UsuarioDao dao = new UsuarioDao();
        if (!dao.existsUser(email, password)){
            // Generar el código de verificación y enviarlo por correo electrónico
            try {
                User usuario = new User(email, password);
                System.out.println("Generando código de verificación");
                String verificationCode = GenerationCode.generateVerificationCode();
                GmailSenderVerify verification = new GmailSenderVerify();
                verification.sendVerificationEmail(email, verificationCode);

                // Guardar el usuario y el código de verificación en la sesión
                HttpSession session = req.getSession();
                session.setAttribute("registerUser", usuario);
                session.setAttribute("verificationCode", verificationCode);

                System.out.println("Redirigiendo a verifyAccount.jsp");
                resp.sendRedirect("verifyAccount.jsp");
            } catch (Exception e) {
                System.out.println("Error al enviar el correo: " + e.getMessage());
                req.setAttribute("errorMessage", "¡Error al enviar el correo de verificación!");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        } else {
            try {
                req.setAttribute("errorMessage", "¡El usuario ya existe!");
                resp.sendRedirect("register.jsp");

            } catch (Exception e){
                System.out.println("El correo ya esta siendo usado");
            }
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
