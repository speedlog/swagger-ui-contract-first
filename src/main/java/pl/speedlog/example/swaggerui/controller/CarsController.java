package pl.speedlog.example.swaggerui.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import pl.speedlog.example.swaggerui.api.CarsApi;
import pl.speedlog.example.swaggerui.model.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">mariusz@wyszomierski.pl</a>
 */
@Controller
public class CarsController implements CarsApi {

    @Override
    public ResponseEntity<List<Car>> getCars() {
        List<Car> cars = new ArrayList<>();
        addTeslaToResponse(cars);
        addVolkswagenToReponse(cars);
        return ResponseEntity.ok(cars);
    }

    private void addTeslaToResponse(List<Car> cars) {
        Car tesla = new Car();
        tesla.name("Tesla Model S");
        tesla.productionYear(2020);
        cars.add(tesla);
    }

    private void addVolkswagenToReponse(List<Car> cars) {
        Car volkswagen = new Car();
        volkswagen.name("Volkswagen Passat B6");
        volkswagen.productionYear(2004);
        cars.add(volkswagen);
    }
}
