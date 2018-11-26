package pl.dormitorymaintenancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dormitorymaintenancesystem.enums.UserStatusEnum;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;

import java.util.List;

public interface InhabitantRepository extends JpaRepository<Inhabitant, Long> {
    Inhabitant findByEmail(String email);

    List<Inhabitant> findAllByUserStatusNot(UserStatusEnum userStatusEnum);
}
