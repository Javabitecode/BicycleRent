package trokhimchuk.bicycle.repo;

import org.springframework.data.repository.CrudRepository;
import trokhimchuk.bicycle.Entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
}
