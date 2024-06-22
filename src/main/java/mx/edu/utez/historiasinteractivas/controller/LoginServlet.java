package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

        UsuarioDao dao = new UsuarioDao();

        //Si el usuario esta vacio
        Users usr = dao.getOne(email, password);
            if(usr.getEmail() == null){
                //Es porque no existe en la base de datos
                System.out.println("El usuario " + email + " No existe en la BD");

                HttpSession session = req.getSession();
                session.setAttribute("mensaje","El usuario no existe en la BD");

                resp.sendRedirect("login.jsp");
            }else{
                //Si existe en la base de datos
                System.out.println("El usuario " + email + " Si esta en la BD");
                resp.sendRedirect("index.jsp");
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
