package pl.speedlog.example.swaggerui.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import pl.speedlog.example.swaggerui.api.AnimalsApi;
import pl.speedlog.example.swaggerui.model.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">mariusz@wyszomierski.pl</a>
 */
@Controller
public class AnimalsController implements AnimalsApi {

    @Override
    public ResponseEntity<List<Animal>> allAnimals() {
        List<Animal> animals = new ArrayList<>();
        addDogToResponse(animals);
        addSharkToResponse(animals);
        return ResponseEntity.ok(animals);
    }

    private void addDogToResponse(List<Animal> animals) {
        Animal dog = new Animal();
        dog.setName("Dog");
        dog.setMammal(true);
        animals.add(dog);
    }

    private void addSharkToResponse(List<Animal> animals) {
        Animal shark = new Animal();
        shark.setName("Shark");
        shark.setMammal(false);
        animals.add(shark);
    }
}
