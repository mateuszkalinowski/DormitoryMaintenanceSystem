package pl.dormitorymaintenancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dormitorymaintenancesystem.model.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
