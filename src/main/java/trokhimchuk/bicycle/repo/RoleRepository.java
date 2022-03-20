package trokhimchuk.bicycle.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import trokhimchuk.bicycle.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
