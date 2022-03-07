package trokhimchuk.bicycle.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trokhimchuk.bicycle.Entity.BicycleEntity;
import trokhimchuk.bicycle.Entity.UserEntity;
import trokhimchuk.bicycle.repo.BicycleRepository;

@RestController

public class RentController {
    final
    BicycleRepository bicycleRepository;
    @Autowired
    public RentController(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @RequestMapping("getBicycles")
    @GetMapping
    public Iterable<BicycleEntity> list() {
        return bicycleRepository.findAll();
    }




/*    //@AuthenticationPrincipal UserEntity userentity;

    @PutMapping("{id}")
    public UserEntity bicycleRent(@PathVariable("id") UserEntity userFromDB,
                             @RequestBody UserEntity userEntity) {
        BeanUtils.copyProperties(userEntity, userFromDB, "id"); //Исправить
        return userRepository.save(userFromDB);
    }*/
}
