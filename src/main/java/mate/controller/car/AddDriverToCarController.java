package mate.controller.car;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.controller.IndexController;
import mate.lib.Injector;
import mate.model.Car;
import mate.model.Driver;
import mate.service.CarService;
import mate.service.DriverService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddDriverToCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private static final Logger logger = LogManager.getLogger(IndexController.class);
    private final CarService carService = (CarService) injector
            .getInstance(CarService.class);
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("Method doGet() was called.");
        req.getRequestDispatcher("/WEB-INF/views/cars/drivers/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        logger.info("Method doPost() was called. Params: req = {}, resp = {}", req, resp);
        long driverId = Long.parseLong(req.getParameter("driver_id"));
        long carId = Long.parseLong(req.getParameter("car_id"));
        Driver driver = driverService.get(driverId);
        Car car = carService.get(carId);
        carService.addDriverToCar(driver, car);
        logger.info("The driver was successfully add to the car."
                + " Param: carId = {}, driverId = {}", carId, driverId);
        resp.sendRedirect("/cars/drivers/add");
    }
}
