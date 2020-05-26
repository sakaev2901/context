package controllers;

import ru.itis.Controller;
import ru.itis.Mapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Mapping("/")
public class GreetingPageController implements Controller {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        try {
            response.getWriter().println("H");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
