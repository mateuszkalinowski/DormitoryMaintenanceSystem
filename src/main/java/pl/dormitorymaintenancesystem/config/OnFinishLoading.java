package pl.dormitorymaintenancesystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.dormitorymaintenancesystem.enums.DormitoryNameEnum;
import pl.dormitorymaintenancesystem.enums.UserRoleEnum;
import pl.dormitorymaintenancesystem.model.*;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;
import pl.dormitorymaintenancesystem.model.users.Worker;
import pl.dormitorymaintenancesystem.repositories.*;

import javax.transaction.Transactional;

@Component
@Transactional
public class OnFinishLoading implements ApplicationListener<ContextRefreshedEvent> {

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

    public void onApplicationEvent(ContextRefreshedEvent event) {


//        inhabitantRepository.save(new Inhabitant("student@local",passwordEncoder.encode("password"),"Jan","Kowalski",106));
//        workerRepository.save(new Worker("worker@local",passwordEncoder.encode("password"),"Zbigniew","Nowak","505606707"));
//        workerRepository.save(new Worker("worker2@local",passwordEncoder.encode("password"),"Zbigniew","Nowak","505606707"));

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

        Worker worker = new Worker("worker3@local",passwordEncoder.encode("password"),"Antoni","Figurski",new UserRole(UserRoleEnum.WORKER),"123456789");
        worker.getCategories().add(category);
        category.getWorkers().add(worker);
        workerRepository.save(worker);



//        // categoryRepository.getTopByCategory("Hydraulika").getWorkers().add(user4.getWorker());
//        user4.getWorker().getCategories().add(categoryRepository.getTopByCategory("Elektryka"));
//        //categoryRepository.getTopByCategory("Elektryka").getWorkers().add(user4.getWorker());
//        user4.getWorker().getCategories().add(categoryRepository.getTopByCategory("Stolarka"));
//        userRepository.save(user4);



        messageRepository.save(new Message("Brak ciepłej wody","W dniach 4-5 września 2018 nie będzie ciepłej wody",workerRepository.findByEmail("worker@local")));
        messageRepository.save(new Message("Zamknięte główne drzwi","\"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.\"",workerRepository.findByEmail("worker@local")));
        messageRepository.save(new Message("Brak prądu1","W dniach 22-23 paźniernika nie będzie prądu",workerRepository.findByEmail("worker@local")));
        messageRepository.save(new Message("Brak prądu2","W dniach 22-23 paźniernika nie będzie prądu",workerRepository.findByEmail("worker@local")));
        messageRepository.save(new Message("Brak prądu3","W dniach 22-23 paźniernika nie będzie prądu",workerRepository.findByEmail("worker@local")));
        messageRepository.save(new Message("Brak prądu4","W dniach 22-23 paźniernika nie będzie prądu",workerRepository.findByEmail("worker@local")));
        messageRepository.save(new Message("Brak prądu5","W dniach 22-23 paźniernika nie będzie prądu",workerRepository.findByEmail("worker@local")));

    }
}

