package trokhimchuk.bicycle.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import trokhimchuk.bicycle.domain.User;

public interface UserRepository extends CrudRepository<User,Long> {
}
