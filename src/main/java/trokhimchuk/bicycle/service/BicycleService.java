package trokhimchuk.bicycle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trokhimchuk.bicycle.Entity.BicycleEntity;
import trokhimchuk.bicycle.model.Bicycle;
import trokhimchuk.bicycle.repo.BicycleRepository;
import trokhimchuk.bicycle.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BicycleService {
    private final BicycleRepository bicycleRepository;
    final UserRepository userRepository;

    public BicycleService(BicycleRepository bicycleRepository, UserRepository userRepository) {
        this.bicycleRepository = bicycleRepository;
        this.userRepository = userRepository;
    }

    public List<Bicycle> getBicycles(Iterable<BicycleEntity> bicycleEntity) {
        List<Bicycle> bicycles = new ArrayList<>();

        for (BicycleEntity bicycleE : bicycleEntity) {
            bicycles.add(Bicycle.toModel(bicycleE));
        }
        return bicycles;
    }


}

