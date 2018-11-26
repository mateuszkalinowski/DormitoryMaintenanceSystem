package pl.dormitorymaintenancesystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.dormitorymaintenancesystem.enums.TaskStatusEnum;
import pl.dormitorymaintenancesystem.model.Category;
import pl.dormitorymaintenancesystem.model.Task;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;
import pl.dormitorymaintenancesystem.model.users.Worker;
import pl.dormitorymaintenancesystem.repositories.CategoryRepository;
import pl.dormitorymaintenancesystem.repositories.InhabitantRepository;
import pl.dormitorymaintenancesystem.service.InhabitantTaskService;
import pl.dormitorymaintenancesystem.utils.dataInput.NewTaskDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class InhabitantTaskControllerTest {

    private MockMvc mockMvc;

    private InhabitantTaskController inhabitantTaskController;

    @InjectMocks
    private InhabitantTaskService inhabitantTaskService;

    @Mock
    private InhabitantRepository inhabitantRepository;

    @Mock
    private CategoryRepository categoryRepository;


    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        inhabitantTaskController = new InhabitantTaskController(inhabitantTaskService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(inhabitantTaskController)
                .build();
    }


    @Test
    @WithMockUser(username = "student@local")
    public void addRequest() throws Exception {
        Inhabitant inhabitant = new Inhabitant();
        inhabitant.setId(0L);
        inhabitant.setRoom(null);
        inhabitant.setFirstName("Jan");
        inhabitant.setFirstName("Kowalski");

        Mockito.when(inhabitantRepository.findByEmail("student@local")).thenReturn(inhabitant);

        Category category = new Category();
        category.setCategory("SampleCategory");
        category.setId(0L);

        Mockito.when(categoryRepository.getTopByCategory("SampleCategory")).thenReturn(category);

        NewTaskDTO newTaskDTO = new NewTaskDTO();
        newTaskDTO.setTitle("SampleTitle");
        newTaskDTO.setDescription("SampleDescription");
        newTaskDTO.setCategory("SampleCategory");

        mockMvc.perform(post("http://localhost:8080/api/inhabitant/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTaskDTO)))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "student@local")
    public void getAllRequestsByInhabitant() throws Exception {


        Inhabitant inhabitant = new Inhabitant();
        inhabitant.setId(0L);
        inhabitant.setRoom(null);
        inhabitant.setFirstName("Jan");
        inhabitant.setFirstName("Kowalski");

        Category category = new Category();
        category.setCategory("SampleCategory");
        category.setId(0L);

        Mockito.when(inhabitantRepository.findByEmail("student@local")).thenReturn(inhabitant);

        Worker worker = new Worker();
        worker.setFirstName("Jan");
        worker.setLastName("Kowalski");
        worker.setContactEmail("");

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

        ArrayList<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(task);

        inhabitant.setTaskList(taskArrayList);

        mockMvc.perform(get("http://localhost:8080/api/inhabitant/task?page=0&size=5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content",hasSize(1)))
                .andExpect(jsonPath("$.content[0].title").value("SampleTitle"));


    }
}