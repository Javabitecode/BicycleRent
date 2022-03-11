package trokhimchuk.bicycle.repo;

import org.springframework.data.repository.CrudRepository;
import trokhimchuk.bicycle.Entity.BicycleEntity;
import trokhimchuk.bicycle.Entity.UserEntity;

import java.util.List;

public interface BicycleRepository extends CrudRepository<BicycleEntity, Long> {
    BicycleEntity findByUserEntity(UserEntity userEntity);
    List<BicycleEntity> findAllByUserEntity_Id(Long id);
}
