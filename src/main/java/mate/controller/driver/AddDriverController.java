package mate.controller.driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.controller.IndexController;
import mate.lib.Injector;
import mate.model.Driver;
import mate.service.DriverService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private static final Logger logger = LogManager.getLogger(IndexController.class);
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("Method doGet() was called.");
        req.getRequestDispatcher("/WEB-INF/views/drivers/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        logger.info("Method doPost() was called.");
        String name = req.getParameter("name");
        String licenseNumber = req.getParameter("license_number");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Driver driver = new Driver(name, licenseNumber, username, password);
        driverService.create(driver);
        logger.info("The driver was created successfully. Param: username = {}", username);
        resp.sendRedirect("/drivers/add");
    }
}
