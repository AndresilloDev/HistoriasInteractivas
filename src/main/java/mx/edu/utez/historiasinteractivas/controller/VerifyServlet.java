package mx.edu.utez.historiasinteractivas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.historiasinteractivas.dao.UsuarioDao;
import mx.edu.utez.historiasinteractivas.model.User;

import java.io.IOException;

@WebServlet(name = "VerifyServlet", value = "/verifyAccount")
public class VerifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String num3 = req.getParameter("num3");
        String num4 = req.getParameter("num4");
        String num5 = req.getParameter("num5");
        String num6 = req.getParameter("num6");

        String enteredCode = num1 + num2 + num3 + num4 + num5 + num6;

        HttpSession session = req.getSession();
        String verificationCode = (String) session.getAttribute("verificationCode");
        User user = (User) session.getAttribute("registerUser");

        if (verificationCode != null && verificationCode.equals(enteredCode)) {
            // Si el código es verificado correctamente se registrar el usuario
            try {
                UsuarioDao usuarioDao = new UsuarioDao();
                if (usuarioDao.insert(user)) {
                    session.invalidate();
                    session = req.getSession();
                    session.setAttribute("user", user);
                    resp.sendRedirect("index.jsp"); // Redirigir a index
                } else {
                    req.setAttribute("errorMessage", "¡Error al registrar el usuario!");
                    req.getRequestDispatcher("verifyAccount.jsp").forward(req, resp);
                }
            } catch (Exception e) {
                req.setAttribute("errorMessage", "Ha ocurrido un error, vuelva a intentarlo más tarde");
                req.getRequestDispatcher("verifyAccount.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMessage", "Código de verificación incorrecto");
            req.getRequestDispatcher("verifyAccount.jsp").forward(req, resp);
        }
    }
}
