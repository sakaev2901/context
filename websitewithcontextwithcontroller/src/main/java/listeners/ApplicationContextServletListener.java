package listeners;


import ru.itis.ApplicationContext;
import ru.itis.ApplicationContextReflectionBased;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ApplicationContext applicationContext = new ApplicationContextReflectionBased();
        ApplicationContext applicationContext =  new ApplicationContextReflectionBased();
        ServletContext context = sce.getServletContext();
        context.setAttribute("applicationContext", applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
