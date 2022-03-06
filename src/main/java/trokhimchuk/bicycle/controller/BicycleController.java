package trokhimchuk.bicycle.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trokhimchuk.bicycle.domain.Bicycle;
import trokhimchuk.bicycle.repo.BicycleRepository;

@RestController
@RequestMapping("bicycle")
public class BicycleController {

    final BicycleRepository bicycleRepository;

    @Autowired
    public BicycleController(BicycleRepository bicycleRepo) {
        this.bicycleRepository = bicycleRepo;
    }

    @GetMapping
    public Iterable<Bicycle> list() {
        return bicycleRepository.findAll();
    }

    @GetMapping("{id}")
    public Bicycle getBicycle(@PathVariable("id") Bicycle bicycle) {
        return bicycle;
    }

    @PostMapping
    public Bicycle create(@RequestBody Bicycle bicycle) {
        return bicycleRepository.save(bicycle);
    }

    @PutMapping("{id}")
    public Bicycle update(@PathVariable("id") Bicycle bicycleFromDB,
                          @RequestBody Bicycle bicycle) {
        BeanUtils.copyProperties(bicycle, bicycleFromDB, "id");
        return bicycleRepository.save(bicycleFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Bicycle bicycle) {
        bicycleRepository.delete(bicycle);

    }

}


