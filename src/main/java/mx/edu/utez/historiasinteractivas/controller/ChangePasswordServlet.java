package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;
import mx.edu.utez.historiasinteractivas.model.User;

import java.io.IOException;

@WebServlet(name="ChangePasswordServlet", value="/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Conseguimos la info del formulario donde los inputs se llamen asi:
        String email = req.getParameter("email");
        String token = req.getParameter("token");
        String new_password = req.getParameter("new_password");
        String confirm_new_password = req.getParameter("confirm_new_password");

        UsuarioDao dao = new UsuarioDao();
        User usuario = dao.getUserFromEmailANDToken(email, token);

        if (usuario.getToken()!=null && !usuario.getToken().equals(token)) { //Se verifica que el token es correcto
            req.setAttribute("errorMessage", "¡El token no es correcto!");
        } else { //El token es correcto
            if (!new_password.equals(confirm_new_password)){ //Se verifica que las contraseñas son iguales
                req.setAttribute("errorMessage", "¡Las contraseñas no coinciden!");
            } else { //Las contraseñas son iguales
                dao.updateUserPassword(email, new_password);
                req.setAttribute("updateMessage", "Contraseña actualizada!");

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