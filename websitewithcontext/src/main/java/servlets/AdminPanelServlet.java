package servlets;

import models.Product;
import ru.itis.ApplicationContext;
import ru.itis.ApplicationContextReflectionBased;
import services.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminPanelServlet extends HttpServlet {

    private ProductService service;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        Object rawAttribute = servletContext.getAttribute("applicationContext");
        ApplicationContext applicationContext = (ApplicationContextReflectionBased) rawAttribute;
        this.service = applicationContext.getComponent(ProductService.class, "productServiceImpl");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/adminPanel.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Product product = Product.builder()
                .name(req.getParameter("name"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
        service.addProduct(product);
        resp.sendRedirect("shop");
    }
}
