package trokhimchuk.bicycle.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trokhimchuk.bicycle.Entity.BicycleEntity;
import trokhimchuk.bicycle.Entity.UserEntity;
import trokhimchuk.bicycle.repo.BicycleRepository;
import trokhimchuk.bicycle.repo.UserRepository;

import java.security.Principal;

@Service
public class RentService {
    private final BicycleRepository bicycleRepository;
    private final UserRepository userRepository;

    public RentService(UserRepository userRepository, BicycleRepository bicycleRepository) {
        this.userRepository = userRepository;
        this.bicycleRepository = bicycleRepository;
    }

    public ResponseEntity getBicycle(BicycleEntity bicycleEntity, Principal principal) {

        BicycleEntity bicycleFromDB = bicycleRepository.findById(bicycleEntity.getId()).get();
        UserEntity userFromDB = userRepository.findByUsername(principal.getName());
        if (bicycleFromDB.getRented() == true) {
            return ResponseEntity.badRequest().body("The bike is busy");
        }
        if (userFromDB.getBicycleCount() >= 2) {
            return ResponseEntity.badRequest().body("Maximum number of bicycles");
        }
        bicycleFromDB.setRented(true);
        bicycleFromDB.setUserEntity(userFromDB);

        userFromDB.setBicycleCount(userFromDB.getBicycleCount() + 1);

        userRepository.save(userFromDB);
        bicycleRepository.save(bicycleFromDB);
        return ResponseEntity.ok("Bike successfully rented");
    }

    public ResponseEntity returnBicycle(BicycleEntity bicycleEntity, UserEntity userEntity) {
        BicycleEntity bicycleFromDB = bicycleRepository.findById(bicycleEntity.getId()).get();
        UserEntity userFromDB = userRepository.findById(userEntity.getId()).get();
        if (bicycleFromDB == null && userEntity == null) {
            return ResponseEntity.badRequest().body("Error: (1.Log in; 2.The bike does not exist;)");
        }

        if (!bicycleFromDB.getUserEntity().getId().equals(userFromDB.getId())) {
            return ResponseEntity.badRequest().body("Error: ");
        }

        userFromDB.setBicycleCount(userFromDB.getBicycleCount() - 1);

        bicycleFromDB.setRented(false);
        bicycleFromDB.setUserEntity(null);
        bicycleRepository.save(bicycleFromDB);
        userRepository.save(userFromDB);
        return ResponseEntity.ok("Bike successfully returned");
    }
}
