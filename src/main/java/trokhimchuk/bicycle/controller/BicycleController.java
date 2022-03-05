package trokhimchuk.bicycle.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trokhimchuk.bicycle.domain.Bicycle;
import trokhimchuk.bicycle.repo.BicycleRepository;

import java.util.List;

@RestController
@RequestMapping("bicycle")
public class BicycleController {


    final BicycleRepository bicycleRepo;

    @Autowired
    public BicycleController(BicycleRepository bicycleRepo) {
        this.bicycleRepo = bicycleRepo;
    }

    @GetMapping
    public List<Bicycle> list() {
        return bicycleRepo.findAll();
    }

    @GetMapping("{id}")
    public Bicycle getBicycle(@PathVariable("id") Bicycle bicycle) {
        return bicycle;
    }

    @PostMapping
    public Bicycle create(@RequestBody Bicycle bicycle) {
        return bicycleRepo.save(bicycle);
    }

    @PutMapping("{id}")
    public Bicycle update(@PathVariable("id") Bicycle bicycleFromDB,
                          @RequestBody Bicycle bicycle) {
        BeanUtils.copyProperties(bicycle, bicycleFromDB, "id");
        return bicycleRepo.save(bicycleFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Bicycle bicycle) {
        bicycleRepo.delete(bicycle);

    }

}


