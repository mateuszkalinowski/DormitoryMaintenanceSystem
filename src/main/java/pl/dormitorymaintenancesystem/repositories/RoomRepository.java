package pl.dormitorymaintenancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dormitorymaintenancesystem.enums.DormitoryNameEnum;
import pl.dormitorymaintenancesystem.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findRoomByRoomNumberEqualsAndDormitoryNameEnumEquals(String roomNumber, DormitoryNameEnum dormitoryNameEnum);
}
