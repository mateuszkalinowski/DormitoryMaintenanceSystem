package pl.dormitorymaintenancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dormitorymaintenancesystem.model.users.Administrator;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Administrator findByEmail(String email);
}
