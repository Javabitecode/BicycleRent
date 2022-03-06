package trokhimchuk.bicycle.repo;

import org.springframework.data.repository.CrudRepository;
import trokhimchuk.bicycle.Entity.BicycleEntity;

public interface BicycleRepository extends CrudRepository<BicycleEntity, Long> {
}
