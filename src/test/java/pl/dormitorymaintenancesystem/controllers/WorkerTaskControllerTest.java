package pl.dormitorymaintenancesystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.dormitorymaintenancesystem.enums.DormitoryNameEnum;
import pl.dormitorymaintenancesystem.enums.TaskStatusEnum;
import pl.dormitorymaintenancesystem.model.Category;
import pl.dormitorymaintenancesystem.model.Room;
import pl.dormitorymaintenancesystem.model.Task;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;
import pl.dormitorymaintenancesystem.model.users.Worker;
import pl.dormitorymaintenancesystem.repositories.*;
import pl.dormitorymaintenancesystem.service.WorkerTaskService;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class WorkerTaskControllerTest {

    private MockMvc mockMvc;

    private WorkerTaskController workerTaskController;

    @InjectMocks
    private WorkerTaskService workerTaskService;

    @Mock
    private WorkerRepository workerRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private CategoryRepository categoryRepository;


    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        workerTaskController = new WorkerTaskController(workerTaskService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(workerTaskController)
                .build();
    }


    @Test
    @WithMockUser(username = "hydraulik@local")
    public void getAllWaitingTasks() throws Exception {

        Category category = new Category();
        category.setCategory("Category1");
        category.setId(0L);

        Category category2 = new Category();
        category2.setCategory("Category2");
        category2.setId(0L);

        Worker worker = new Worker();
        worker.setFirstName("Jan");
        worker.setLastName("Kowalski");
        worker.setContactEmail("");

        ArrayList<Category> categories = new ArrayList<>();
        categories.add(category);
        worker.setCategories(categories);


        when(workerRepository.findByEmail("hydraulik@local")).thenReturn(worker);

        ArrayList<Task> tasksList = new ArrayList<>();



        Inhabitant inhabitant = new Inhabitant();
        inhabitant.setId(0L);
        inhabitant.setRoom(null);
        inhabitant.setFirstName("Jan");
        inhabitant.setFirstName("Kowalski");
        Room room = new Room();
        room.setDormitoryNameEnum(DormitoryNameEnum.BRATNIAK);
        room.setRoomNumber("234");
        inhabitant.setRoom(room);


        Task task = new Task();
        task.setTitle("SampleTitle");
        task.setComment("SampleComment");
        task.setStatus(TaskStatusEnum.TASK_WAITING);
        task.setWorker(worker);
        task.setInhabitant(inhabitant);
        task.setTimeStamp(LocalDateTime.now());
        task.setId(0L);
        task.setContent("");
        task.setCategory(category);

        tasksList.add(task);


        Task task2 = new Task();
        task2.setTitle("SampleTitle");
        task2.setComment("SampleComment");
        task2.setStatus(TaskStatusEnum.TASK_WAITING);
        task2.setWorker(worker);
        task2.setInhabitant(inhabitant);
        task2.setTimeStamp(LocalDateTime.now());
        task2.setId(0L);
        task2.setContent("");
        task2.setCategory(category2);

        tasksList.add(task2);

        when(taskRepository.findAllByWorkerIsNullOrderByTimeStampDesc()).thenReturn(tasksList);

        mockMvc.perform(get("http://localhost:8080/api/worker/task/waitingTasks?page=0&size=5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content",hasSize(1)));

    }


}