package trokhimchuk.bicycle.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import trokhimchuk.bicycle.domain.Bicycle;

public interface BicycleRepo extends JpaRepository<Bicycle, Long> {
}
