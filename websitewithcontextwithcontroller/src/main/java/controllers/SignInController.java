package controllers;

import models.User;
import ru.itis.Controller;
import ru.itis.Mapping;
import services.SignInService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Mapping("/signIn")
public class SignInController implements Controller {
    SignInService service;

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
        try {
            RequestDispatcher requestDispatcher = request.getSession().getServletContext().getRequestDispatcher("/signIn.ftl");
            System.out.println(requestDispatcher);
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        User user = service.signIn(request.getParameter("login"), request.getParameter("password"));
        HttpSession session = request.getSession();
        session.setAttribute("id", user.getId());
        session.setAttribute("role", user.getRole());
        try {
            response.sendRedirect("profile");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
