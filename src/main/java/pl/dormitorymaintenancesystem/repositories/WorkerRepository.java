package pl.dormitorymaintenancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dormitorymaintenancesystem.model.users.Worker;

public interface WorkerRepository extends JpaRepository<Worker,Long> {
    Worker findByEmail(String email);
}
