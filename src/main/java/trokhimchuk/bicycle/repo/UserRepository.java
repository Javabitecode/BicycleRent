package trokhimchuk.bicycle.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import trokhimchuk.bicycle.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
