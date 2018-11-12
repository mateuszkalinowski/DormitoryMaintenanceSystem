package pl.dormitorymaintenancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dormitorymaintenancesystem.model.UserRole;

public interface UserRolesRepository extends JpaRepository<UserRole, Long> {
;
}
