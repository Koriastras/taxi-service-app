package mate.controller.manufacturer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.controller.IndexController;
import mate.lib.Injector;
import mate.model.Manufacturer;
import mate.service.ManufacturerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private static final Logger logger = LogManager.getLogger(IndexController.class);
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("Method doGet() was called.");
        req.getRequestDispatcher("/WEB-INF/views/manufacturers/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        logger.info("Method doPost() was called.");
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturerService.create(manufacturer);
        logger.info("The manufacturer was created successfully."
                + " Param: name = {}, country = {}", name, country);
        resp.sendRedirect("/manufacturers/add");
    }
}
