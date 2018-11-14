package pl.dormitorymaintenancesystem.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.dormitorymaintenancesystem.enums.DormitoryNameEnum;
import pl.dormitorymaintenancesystem.enums.TaskStatusEnum;
import pl.dormitorymaintenancesystem.enums.UserRoleEnum;
import pl.dormitorymaintenancesystem.model.*;
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
    private InhabitantRepository inhabitantRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MessageRepository messageRepository;
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
        inhabitantRepository.save(user);

        Worker user2 = new Worker("worker@local",passwordEncoder.encode("password"),"Zbigniew","Nowak",new UserRole(UserRoleEnum.WORKER),"505404303");
        workerRepository.save(user2);

        Inhabitant user3 = new Inhabitant("student2@local",passwordEncoder.encode("password"),"Michał","Gornolewski",new UserRole(UserRoleEnum.INHABITANT));
        user3.setRoom(room);
        userRepository.save(user3);

        room.getInhabitantList().add(user);
        room.getInhabitantList().add(user3);

        roomRepository.save(room);


        Category category = categoryRepository.getTopByCategory("Hydraulika");

        Worker worker = new Worker("hydraulik@local",passwordEncoder.encode("password"),"Antoni","Figurski",new UserRole(UserRoleEnum.WORKER),"123456789");
        worker.getCategories().add(category);
        worker.setContactEmail("hydraulik1@wp.pl");
        category.getWorkers().add(worker);
        workerRepository.save(worker);


        messageRepository.save(new Message("Brak ciepłej wody","W dniach 4-5 września 2018 nie będzie ciepłej wody",workerRepository.findByEmail("worker@local")));
        messageRepository.save(new Message("Zamknięte główne drzwi","\"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.\"",workerRepository.findByEmail("worker@local")));
        messageRepository.save(new Message("Brak prądu1","W dniach 22-23 paźniernika nie będzie prądu",workerRepository.findByEmail("worker@local")));
        messageRepository.save(new Message("Brak prądu2","W dniach 22-23 paźniernika nie będzie prądu",workerRepository.findByEmail("worker@local")));
        messageRepository.save(new Message("Brak prądu3","W dniach 22-23 paźniernika nie będzie prądu",workerRepository.findByEmail("worker@local")));
        messageRepository.save(new Message("Brak prądu4","W dniach 22-23 paźniernika nie będzie prądu",workerRepository.findByEmail("worker@local")));
        messageRepository.save(new Message("Brak prądu5","W dniach 22-23 paźniernika nie będzie prądu",workerRepository.findByEmail("worker@local")));

        Task task = new Task();
        task.setTitle("Problem z umywalką");
        task.setContent("Uszkodzona uszczelka w kranie powoduje że woda przecieka nawet po delikatnym odkręceniu");
        task.setCategory(category);
        task.setInhabitant(user);
        task.setComment("");
        task.setTimeStamp(LocalDateTime.now());
        task.setStatus(TaskStatusEnum.REQUEST_WAITING);
        taskRepository.save(task);

    }
}
