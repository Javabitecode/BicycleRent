package trokhimchuk.bicycle.repo;

import org.springframework.data.repository.CrudRepository;
import trokhimchuk.bicycle.Entity.BicycleEntity;
import trokhimchuk.bicycle.Entity.UserEntity;

public interface BicycleRepository extends CrudRepository<BicycleEntity, Long> {
    BicycleEntity findByUserEntity(UserEntity userEntity);
}
