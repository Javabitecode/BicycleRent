package trokhimchuk.bicycle.controller;

import org.apache.tomcat.util.http.parser.Authorization;
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
import trokhimchuk.bicycle.service.RentService;

import java.security.Principal;
import java.util.List;

@RestController

public class RentController {
    private final UserRepository userRepository;
    private final BicycleService bicycleService;
    private final BicycleRepository bicycleRepository;
    private final RentService rentService;

    @Autowired
    public RentController(BicycleRepository bicycleRepository, BicycleService bicycleService, RentService rentService, UserRepository userRepository) {
        this.bicycleRepository = bicycleRepository;
        this.bicycleService = bicycleService;
        this.rentService = rentService;
        this.userRepository = userRepository;
    }

    @GetMapping("getYourBicycles")
    public List<BicycleEntity> getYourBicycle(@AuthenticationPrincipal UserEntity userEntity) {
        return bicycleRepository.findAllByUserEntity_Id(userEntity.getId());
    }

    @GetMapping("getBicycles")
    public List<Bicycle> list() {
        return bicycleService.getBicycles(bicycleRepository.findAll());
    }

    @PutMapping("rentBicycle")
    public ResponseEntity getBicycle(@RequestBody BicycleEntity bicycleEntity,
                                     Principal principal) {
        return rentService.getBicycle(bicycleEntity, principal);
    }

    @PutMapping("returnBicycle")
    public ResponseEntity returnBicycle(@RequestBody BicycleEntity bicycleEntity,
                                        @AuthenticationPrincipal UserEntity userEntity) {
        try {
            return rentService.returnBicycle(bicycleEntity, userEntity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error ");
        }
    }
}


