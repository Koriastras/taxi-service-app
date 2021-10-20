package mate.controller.car;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.controller.IndexController;
import mate.lib.Injector;
import mate.model.Car;
import mate.model.Manufacturer;
import mate.service.CarService;
import mate.service.ManufacturerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private static final Logger logger = LogManager.getLogger(IndexController.class);
    private final CarService carService = (CarService) injector
            .getInstance(CarService.class);
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("Method doGet() was called.");
        req.getRequestDispatcher("/WEB-INF/views/cars/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        logger.info("Method doPost() was called.");
        String model = req.getParameter("model");
        long manufacturerId = Long.parseLong(req.getParameter("manufacturer_id"));
        Manufacturer manufacturer = manufacturerService.get(manufacturerId);
        Car car = new Car(model, manufacturer);
        carService.create(car);
        logger.info("The car was created successfully."
                + " Param: model = {}, manufacturerId = {}", model, manufacturerId);
        resp.sendRedirect("/cars/add");
    }
}
