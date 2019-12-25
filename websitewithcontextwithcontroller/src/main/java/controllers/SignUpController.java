package controllers;

import ru.itis.Controller;
import ru.itis.Mapping;
import services.SignUpService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Mapping("/signUp")
public class SignUpController implements Controller {

    SignUpService service;

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
            RequestDispatcher requestDispatcher = request.getSession().getServletContext().getRequestDispatcher("/signUp.ftl");
            System.out.println(requestDispatcher);
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        this.service.signUp(request.getParameter("login"), request.getParameter("password"));
        try {
            response.sendRedirect("signIn");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
