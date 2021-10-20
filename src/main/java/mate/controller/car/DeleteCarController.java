package mate.controller.car;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.controller.IndexController;
import mate.lib.Injector;
import mate.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private static final Logger logger = LogManager.getLogger(IndexController.class);
    private final CarService carService = (CarService) injector
            .getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        logger.info("Method doGet() was called.");
        carService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("/cars/all");
    }
}
