package mate.controller.car;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mate.controller.IndexController;
import mate.lib.Injector;
import mate.model.Car;
import mate.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetMyCurrentCarsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private static final Logger logger = LogManager.getLogger(IndexController.class);
    private final CarService carService = (CarService) injector
            .getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("Method doGet() was called.");
        HttpSession session = req.getSession();
        Long driverId = (Long) session.getAttribute("driver_id");
        List<Car> cars = carService.getAllByDriver(driverId);
        req.setAttribute("cars", cars);
        req.getRequestDispatcher("/WEB-INF/views/cars/all.jsp").forward(req, resp);
    }
}
