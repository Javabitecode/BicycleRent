package trokhimchuk.bicycle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import trokhimchuk.bicycle.Entity.BicycleEntity;
import trokhimchuk.bicycle.Entity.UserEntity;
import trokhimchuk.bicycle.model.Bicycle;
import trokhimchuk.bicycle.repo.BicycleRepository;
import trokhimchuk.bicycle.repo.UserRepository;
import trokhimchuk.bicycle.service.BicycleService;

import java.util.List;

@RestController

public class RentController {
    final BicycleService bicycleService;
    final UserRepository userRepository;
    final BicycleRepository bicycleRepository;

    @Autowired
    public RentController(BicycleRepository bicycleRepository, UserRepository userRepository, BicycleService bicycleService) {
        this.bicycleRepository = bicycleRepository;
        this.userRepository = userRepository;
        this.bicycleService = bicycleService;
    }




    @GetMapping("getBicycles")
    public List<Bicycle> list() {
        return bicycleService.getBicycles(bicycleRepository.findAll());
    }

    @PutMapping("getBicycle")
    public ResponseEntity getBicycle(@RequestBody BicycleEntity bicycleEntity,
                                     @AuthenticationPrincipal UserEntity userEntity) {

        BicycleEntity bicycleFromDB = bicycleRepository.findById(bicycleEntity.getId()).get();
        UserEntity userFromDB = userRepository.findById(userEntity.getId()).get();
        if (bicycleFromDB.getRented() == true) {
            return ResponseEntity.badRequest().body("The bike is busy");
        }
        if (userFromDB.getBicycleCount() >= 2) {
            return ResponseEntity.badRequest().body("Maximum number of bicycles");
        }
        bicycleFromDB.setRented(true);
        bicycleFromDB.setUserEntity(userEntity);

        System.out.println(userFromDB.getBicycleCount() + "это из бд");
        System.out.println(userEntity.getBicycleCount() + "before это не из бд");
        userFromDB.setBicycleCount(userFromDB.getBicycleCount() + 1);
        System.out.println(userFromDB.getBicycleCount() + "после это из бд");
        System.out.println(userEntity.getBicycleCount() + "last это не из бд");

        userRepository.save(userFromDB);
        bicycleRepository.save(bicycleFromDB);
        return ResponseEntity.ok("Велосипед успешно арендован");
    }

    @PutMapping("returnBicycle")
    public ResponseEntity returnBicycle(@RequestBody BicycleEntity bicycleEntity,
                                        @AuthenticationPrincipal UserEntity userEntity) {
        BicycleEntity bicycleFromDB = bicycleRepository.findById(bicycleEntity.getId()).get();
        UserEntity userFromDB = userRepository.findById(userEntity.getId()).get();
        if (bicycleFromDB == null && userEntity == null) {
            return ResponseEntity.badRequest().body("Error: (1.Log in; 2.The bike does not exist;)");
        }

        if (bicycleFromDB.getUserEntity().getId()!= userFromDB.getId() && !bicycleFromDB.getRented()){
            return ResponseEntity.badRequest().body("Error: ");
        }

        System.out.println(userFromDB.getBicycleCount() + "это из бд");
        System.out.println(userEntity.getBicycleCount() + "before это не из бд");
        userFromDB.setBicycleCount(userFromDB.getBicycleCount() - 1);
        System.out.println(userFromDB.getBicycleCount() + "после это из бд");
        System.out.println(userEntity.getBicycleCount() + "last это не из бд");

        bicycleFromDB.setRented(false);
        bicycleFromDB.setUserEntity(null);
        bicycleRepository.save(bicycleFromDB);
        userRepository.save(userFromDB);

        return ResponseEntity.ok("Bike successfully returned");
    }
}


