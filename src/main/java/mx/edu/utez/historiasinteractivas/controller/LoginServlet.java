package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;
import mx.edu.utez.historiasinteractivas.model.Users;

import java.io.IOException;

@WebServlet(name="LoginServlet", value="/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Conseguimos la info del formulario
        //donde los inputs se llamen asi:
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        //Se extrae si el usuario existe y su tipo de usuario
        UsuarioDao dao = new UsuarioDao();
        Users user = dao.existsUser(email, password);

        if(user.isStatus() ){ //Se verifica si el usuario existe
            if(user.getUser_type() == 1) { //El usuario es un npc
                resp.sendRedirect("index.jsp");

            } else if(user.getUser_type() == 2){ //El usuario es premium
                resp.sendRedirect("paginaNoCreadaParaAdministrador");

            }
        }else{ // El usuario no existe o no escribio bien su usuario y contraseña
            resp.sendRedirect("login.jsp");

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
