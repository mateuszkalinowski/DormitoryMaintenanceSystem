package pl.dormitorymaintenancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;

public interface InhabitantRepository extends JpaRepository<Inhabitant, Long> {
    Inhabitant findByEmail(String email);
}
