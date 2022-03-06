package trokhimchuk.bicycle.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trokhimchuk.bicycle.Entity.BicycleEntity;
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
    public Iterable<BicycleEntity> list() {
        return bicycleRepository.findAll();
    }

    @GetMapping("{id}")
    public BicycleEntity getBicycle(@PathVariable("id") BicycleEntity bicycleEntity) {
        return bicycleEntity;
    }

    @PostMapping
    public BicycleEntity create(@RequestBody BicycleEntity bicycleEntity) {
        return bicycleRepository.save(bicycleEntity);
    }

    @PutMapping("{id}")
    public BicycleEntity update(@PathVariable("id") BicycleEntity bicycleEntityFromDB,
                                @RequestBody BicycleEntity bicycleEntity) {
        BeanUtils.copyProperties(bicycleEntity, bicycleEntityFromDB, "id");
        return bicycleRepository.save(bicycleEntityFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") BicycleEntity bicycleEntity) {
        bicycleRepository.delete(bicycleEntity);

    }

}


