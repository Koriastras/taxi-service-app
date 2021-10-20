package mate.controller.driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mate.controller.IndexController;
import mate.exception.AuthenticationException;
import mate.lib.Injector;
import mate.model.Driver;
import mate.service.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverLoginController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private static final Logger logger = LogManager.getLogger(IndexController.class);
    private AuthenticationService authenticationService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("Method doGet() was called.");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("Method doPost() was called.");
        authenticationService = (AuthenticationService) injector
                .getInstance(AuthenticationService.class);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            Driver driver = authenticationService.login(username, password);
            HttpSession session = req.getSession();
            session.setAttribute("driver_id", driver.getId());
            resp.sendRedirect("/index");
        } catch (AuthenticationException e) {
            logger.error("Can't authenticate the driver. Params: username = {}",
                    username,
                    e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
