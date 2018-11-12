package pl.dormitorymaintenancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dormitorymaintenancesystem.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
