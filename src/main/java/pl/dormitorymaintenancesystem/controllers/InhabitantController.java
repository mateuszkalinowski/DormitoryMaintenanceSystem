package pl.dormitorymaintenancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dormitorymaintenancesystem.enums.DormitoryNameEnum;
import pl.dormitorymaintenancesystem.enums.UserRoleEnum;
import pl.dormitorymaintenancesystem.model.Room;
import pl.dormitorymaintenancesystem.model.UserRole;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;
import pl.dormitorymaintenancesystem.model.users.User;
import pl.dormitorymaintenancesystem.repositories.InhabitantRepository;
import pl.dormitorymaintenancesystem.repositories.RoomRepository;
import pl.dormitorymaintenancesystem.repositories.UserRepository;
import pl.dormitorymaintenancesystem.utils.dataOutput.InhabitantDTO;
import pl.dormitorymaintenancesystem.utils.dataOutput.MessageDTO;

import javax.transaction.Transactional;


@RestController
@RequestMapping(value = "api/inhabitant")
@Transactional
public class InhabitantController {

    @Autowired
    private InhabitantRepository inhabitantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping(value = "/add")
    public ResponseEntity addInhabitant(@RequestBody InhabitantDTO inhabitantDTO) {
        try {
            User check = userRepository.findByEmail(inhabitantDTO.getEmail());
            if(check!=null)
                return ResponseEntity.badRequest().body(new MessageDTO("Ten adres email jest już użyty"));
            Inhabitant inhabitant = new Inhabitant(inhabitantDTO.getEmail(),passwordEncoder.encode(inhabitantDTO.getPassword()),inhabitantDTO.getFirstName(),inhabitantDTO.getLastName(),new UserRole(UserRoleEnum.INHABITANT));

            String roomNumber = inhabitantDTO.getRoomNumber();
            DormitoryNameEnum dormitoryNameEnum = DormitoryNameEnum.valueOf(inhabitantDTO.getDormitoryName());

            Room room = roomRepository.findRoomByRoomNumberEqualsAndDormitoryNameEnumEquals(roomNumber,dormitoryNameEnum);

            if(room == null) {
                room = new Room();
                room.setDormitoryNameEnum(dormitoryNameEnum);
                room.setRoomNumber(roomNumber);
            }
            room.getInhabitantList().add(inhabitant);
            inhabitant.setRoom(room);

            inhabitantRepository.save(inhabitant);
            roomRepository.save(room);
            return ResponseEntity.ok(new MessageDTO("Użytkownik został dodany"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO("Podczas dodawania mieszkańca wystąpił błąd"));
        }
    }
}
