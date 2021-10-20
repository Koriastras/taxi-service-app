package mate.controller.driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.controller.IndexController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverLogoutController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(IndexController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("Method doGet() was called.");
        req.getSession().invalidate();
        logger.info("Driver was successfully logout");
        resp.sendRedirect("/index");
    }
}
