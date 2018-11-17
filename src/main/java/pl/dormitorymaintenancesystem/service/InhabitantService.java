package pl.dormitorymaintenancesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.enumTranslation.UserStatusTranslation;
import pl.dormitorymaintenancesystem.enums.DormitoryNameEnum;
import pl.dormitorymaintenancesystem.enums.UserRoleEnum;
import pl.dormitorymaintenancesystem.enums.UserStatusEnum;
import pl.dormitorymaintenancesystem.model.Room;
import pl.dormitorymaintenancesystem.model.UserRole;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;
import pl.dormitorymaintenancesystem.model.users.User;
import pl.dormitorymaintenancesystem.repositories.AdministratorRepository;
import pl.dormitorymaintenancesystem.repositories.InhabitantRepository;
import pl.dormitorymaintenancesystem.repositories.RoomRepository;
import pl.dormitorymaintenancesystem.repositories.UserRepository;
import pl.dormitorymaintenancesystem.utils.Page;
import pl.dormitorymaintenancesystem.utils.dataOutput.InhabitantDTO;
import pl.dormitorymaintenancesystem.utils.dataOutput.MessageDTO;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
@Transactional
public class InhabitantService {

    @Autowired
    private InhabitantRepository inhabitantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    public ResponseEntity addInhabitant(InhabitantDTO inhabitantDTO) {
        try {
            if(inhabitantDTO.getEmail() == null|| inhabitantDTO.getFirstName() == null|| inhabitantDTO.getLastName() == null || inhabitantDTO.getPassword() == null|| inhabitantDTO.getRoomNumber() == null || inhabitantDTO.getDormitoryName() == null)
                return ResponseEntity.ok(new MessageDTO("Wypełnij wszystkie pola"));
            User check = userRepository.findByEmail(inhabitantDTO.getEmail());
            if(check!=null)
                return ResponseEntity.badRequest().body(new MessageDTO("Ten adres email jest już użyty"));
            Inhabitant inhabitant = new Inhabitant(inhabitantDTO.getEmail(),passwordEncoder.encode(inhabitantDTO.getPassword()),inhabitantDTO.getFirstName(),inhabitantDTO.getLastName(),new UserRole(UserRoleEnum.INHABITANT));
            inhabitant.setUserStatus(UserStatusEnum.NEW);
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
            return ResponseEntity.ok(new MessageDTO("Użytkownik został dodany. Zalogowanie będzie możliwe " +
                    "po zaakceptowaniu konta przez administratora systemu"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageDTO("Podczas dodawania mieszkańca wystąpił błąd"));
        }
    }

    public ResponseEntity getAllForAdmin(int page, int size){
        try {
            List<HashMap<String,Object>> usersList = new ArrayList<>();

            for(Inhabitant inhabitant : inhabitantRepository.findAll()) {
                HashMap<String,Object> user = new HashMap<>();

                user.put("id",inhabitant.getId());
                user.put("firstName",inhabitant.getFirstName());
                user.put("lastName",inhabitant.getLastName());
                user.put("email",inhabitant.getEmail());
                user.put("roomNumber",inhabitant.getRoom().getRoomNumber() + ", " + inhabitant.getRoom().getDormitoryNameEnum());
                user.put("status", UserStatusTranslation.translateRequestStatus(inhabitant.getUserStatus()));
                usersList.add(user);

            }

            return ResponseEntity.ok().body(Page.createPage(page,size,usersList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
