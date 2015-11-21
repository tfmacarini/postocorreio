package br.com.senai.postocorreio.controller;

import br.com.senai.postocorreio.dao.LoginInvalidoException;
import br.com.senai.postocorreio.dao.UsuarioDAO;
import br.com.senai.postocorreio.model.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    
    @EJB
    private UsuarioDAO usuarioDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String action = req.getParameter("action");
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");

        if ("doLogin".equals(action)) {
            try {

                Usuario usuario = usuarioDAO.login(username, password);

                req.getSession().setAttribute("usuario", usuario);

                Cookie cookieUsuario = new Cookie("lembrarUsuario", username);
                cookieUsuario.setHttpOnly(true);
                cookieUsuario.setMaxAge(60 * 60 * 24 * 30);
                resp.addCookie(cookieUsuario);

                resp.sendRedirect("#/home");
            } catch (LoginInvalidoException | SQLException ex) {
                req.setAttribute("mensagem", ex.getMessage());
                req.getRequestDispatcher("views/login.html").forward(req, resp);
            }
        } else {
            String login = "";
            for (Cookie c : req.getCookies()) {
                if ("lembrarUsuario".equals(c.getName())) {
                    login = c.getValue();
                    break;
                }
            }
            req.setAttribute("login", login);
            req.getRequestDispatcher("views/login.html").forward(req, resp);
        }

    }

}
