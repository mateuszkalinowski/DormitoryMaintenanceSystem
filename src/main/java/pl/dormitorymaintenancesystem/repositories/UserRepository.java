package pl.dormitorymaintenancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dormitorymaintenancesystem.model.users.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
