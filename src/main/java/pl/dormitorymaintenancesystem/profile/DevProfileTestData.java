package pl.dormitorymaintenancesystem.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.dormitorymaintenancesystem.enums.DormitoryNameEnum;
import pl.dormitorymaintenancesystem.enums.TaskStatusEnum;
import pl.dormitorymaintenancesystem.enums.UserRoleEnum;
import pl.dormitorymaintenancesystem.enums.UserStatusEnum;
import pl.dormitorymaintenancesystem.model.*;
import pl.dormitorymaintenancesystem.model.users.Administrator;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;
import pl.dormitorymaintenancesystem.model.users.Worker;
import pl.dormitorymaintenancesystem.repositories.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
@DevProfileInterface
@Transactional
public class DevProfileTestData implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private InhabitantRepository inhabitantRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public void run(String... args) throws Exception {

        System.out.println("Dodaje przykładowe dane");

        categoryRepository.save(new Category("Hydraulika"));
        categoryRepository.save(new Category("Elektryka"));
        categoryRepository.save(new Category("Stolarka"));
        categoryRepository.save(new Category("Portiernia"));
        categoryRepository.save(new Category("Administracja"));
        categoryRepository.save(new Category("Internet"));
        categoryRepository.save(new Category("Rada mieszkańców"));

        Room room = new Room();
        room.setRoomNumber("106");
        room.setDormitoryNameEnum(DormitoryNameEnum.BRATNIAK);

        Inhabitant user = new Inhabitant("student@local",passwordEncoder.encode("password"),"Jan","Kowalski",new UserRole(UserRoleEnum.INHABITANT));
        user.setRoom(room);
        user.setUserStatus(UserStatusEnum.ACCEPTED);
        inhabitantRepository.save(user);

        room.getInhabitantList().add(user);

        roomRepository.save(room);


        Category category = categoryRepository.getTopByCategory("Hydraulika");

        Worker worker = new Worker("hydraulik@local",passwordEncoder.encode("password"),"Antoni","Figurski",new UserRole(UserRoleEnum.WORKER),"123456789");
        worker.getCategories().add(category);
        worker.setContactEmail("hydraulik1@wp.pl");
        worker.setUserStatus(UserStatusEnum.ACCEPTED);
        category.getWorkers().add(worker);
        workerRepository.save(worker);


        Task task = new Task();
        task.setTitle("Problem z umywalką");
        task.setContent("Uszkodzona uszczelka w kranie powoduje że woda przecieka nawet po delikatnym odkręceniu");
        task.setCategory(category);
        task.setInhabitant(user);
        task.setComment("");
        task.setTimeStamp(LocalDateTime.now());
        task.setStatus(TaskStatusEnum.TASK_WAITING);
        taskRepository.save(task);

        Administrator administrator = new Administrator("admin@local",passwordEncoder.encode("password"),"Admin","Admiński",new UserRole(UserRoleEnum.ADMIN));
        administrator.setUserStatus(UserStatusEnum.ACCEPTED);
        administrator.setContactEmail("contact@administrator.pl");
        administrator.setPhoneNumber("785-987-364");
        administratorRepository.save(administrator);

        Announcement adminAnnouncement = new Announcement();
        adminAnnouncement.setTitle("Brak dostępu do systemu");
        adminAnnouncement.setContent("Uwaga: 30 listopada w godzinach 10-12 nie będzie możliwe zalogowanie się do systemu" +
                "z powodu przeprowadzanych prac konserwacyjnych.");
        adminAnnouncement.setTimeStamp(LocalDateTime.now());
        adminAnnouncement.setSender(administrator);
        announcementRepository.save(adminAnnouncement);

        Announcement workerAnnouncement = new Announcement();
        workerAnnouncement.setTitle("Brak ciepłej wody");
        workerAnnouncement.setContent("Uwaga. Z powodu awarii węzła ciepłownicznego brak ciepłej wody w kranach utrzyma" +
                "się jeszcze prawdopodobnie przez około 18 godzin.");
        workerAnnouncement.setTimeStamp(LocalDateTime.now());
        workerAnnouncement.setSender(worker);
        announcementRepository.save(workerAnnouncement);

    }
}
