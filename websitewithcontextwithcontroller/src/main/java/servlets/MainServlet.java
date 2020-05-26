package servlets;


import ru.itis.ApplicationContext;
import ru.itis.Controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    private Controller controller;
    private ApplicationContext applicationContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        Object objectAttribute = servletContext.getAttribute("applicationContext");
        applicationContext = (ApplicationContext) objectAttribute;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] parts = req.getRequestURI().split("/");
        String url = "/" + parts[parts.length - 1];
        System.out.println(url);
        controller = applicationContext.getComponent(Controller.class, url);
        System.out.println(req.getRequestURI());
        System.out.println(controller);
        controller.handle(req, resp, getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] parts = req.getRequestURI().split("/");
        String url = "/" + parts[parts.length - 1];
        System.out.println(url);
        controller = applicationContext.getComponent(Controller.class, url);
        System.out.println(req.getRequestURI());
        System.out.println(controller);
        controller.handle(req, resp, getServletContext());
    }
}
