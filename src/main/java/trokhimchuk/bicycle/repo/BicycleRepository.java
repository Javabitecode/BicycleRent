package trokhimchuk.bicycle.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import trokhimchuk.bicycle.domain.Bicycle;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
}
