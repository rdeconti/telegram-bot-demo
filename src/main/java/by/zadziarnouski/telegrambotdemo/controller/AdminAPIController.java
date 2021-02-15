package by.zadziarnouski.telegrambotdemo.controller;


import by.zadziarnouski.telegrambotdemo.model.City;
import by.zadziarnouski.telegrambotdemo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import java.util.Objects;

@RestController
@Transactional
@RequestMapping("/api-telegram-bot")
public class AdminAPIController {
    private final CityService cityService;

    @Autowired
    public AdminAPIController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/*/get")
    public ResponseEntity<?> get(@RequestParam("city") String city) {
        if (cityService.existsByName(city)) {
            return ResponseEntity.ok()
                    .body(cityService.findByName(city));
        }
        return ResponseEntity.badRequest()
                .header("Result", city + " with that name does not exist")
                .build();
    }

    @PostMapping("/*/create")
    public ResponseEntity<?> create(@RequestBody City city) {

        if (cityService.existsByName(city.getName())) {
            if (Objects.isNull((cityService.findByName(city.getName())))) {
                cityService.save(city);
                return ResponseEntity.ok()
                        .header("Result", city.getName() + " with description added successfully")
                        .build();
            } else return ResponseEntity.badRequest()
                    .header("Result", "There is already a city with '" + city.getName() + "' name")
                    .build();

        } else return ResponseEntity.badRequest()
                .header("Result", "Description cannot be empty")
                .build();
    }

    @PutMapping("/*/update")
    public ResponseEntity<?> update(@RequestBody City city) {

        if (cityService.existsByName(city.getName()) && Objects.nonNull(city.getDescriptions())) {
            cityService.save(city);
            return ResponseEntity.ok()
                    .header("Result", "A city named " + city.getName() + " has been updated")
                    .build();
        } else return ResponseEntity.badRequest()
                .header("Result", "The city named " + city.getName() + " does not exist or the description is empty")
                .build();
    }

    @DeleteMapping("/*/delete/{cityName}")
    public ResponseEntity<?> delete(@PathVariable("cityName") String city) {

        if (cityService.existsByName(city)) {
            cityService.deleteByName(city);
            return ResponseEntity.ok()
                    .header("Result", city + " deleted successfully")
                    .build();
        }
        return ResponseEntity.badRequest()
                .header("Result", city + " with that name does not exist")
                .build();
    }

    @DeleteMapping("/*/delete-last-description/{cityName}")
    public ResponseEntity<?> deleteLastDescription(@PathVariable("cityName") String city) {

        if (cityService.existsByName(city)) {
            City byName = cityService.findByName(city);
            byName.getDescriptions().remove(byName.getDescriptions().size() - 1);
            if (byName.getDescriptions().size() == 0) {
                cityService.deleteByName(city);
                return ResponseEntity.ok()
                        .header("Result", city + " was removed as it only contained one description")
                        .build();
            }
            return ResponseEntity.ok()
                    .header("Result", "Last description of " + city + " has been removed")
                    .build();
        }
        return ResponseEntity.badRequest()
                .header("Result", city + " with that name does not exist")
                .build();
    }
}
