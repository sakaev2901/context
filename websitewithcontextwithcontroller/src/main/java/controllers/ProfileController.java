package controllers;

import models.Role;
import models.User;
import ru.itis.Controller;
import ru.itis.Mapping;
import services.ProfileService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Mapping("/profile")
public class ProfileController implements Controller {

    private ProfileService service;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        String method = request.getMethod();
        if (method.equals("GET")) {
            doGet(request, response);
        } else {
            if (method.equals("POST")) {
                doPost(request, response);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("id");
        Role role = (Role) session.getAttribute("role");
        User user = service.getUser(userId);
        request.setAttribute("login", user.getLogin());
        request.setAttribute("role", role.toString());
        try {
            request.getRequestDispatcher("/profile.ftl").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
//        User user = service.signIn(request.getParameter("login"), request.getParameter("password"));
//        HttpSession session = request.getSession();
//        session.setAttribute("id", user.getId());
//        session.setAttribute("role", user.getRole());
//        response.sendRedirect("profile");
    }
}
