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
import pl.dormitorymaintenancesystem.model.Message;
import pl.dormitorymaintenancesystem.model.users.Employee;
import pl.dormitorymaintenancesystem.repositories.EmployeeRepository;
import pl.dormitorymaintenancesystem.repositories.MessageRepository;
import pl.dormitorymaintenancesystem.service.MessageService;
import pl.dormitorymaintenancesystem.utils.dataInput.NewAnnouncement;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class MessageControllerTest {

    private MockMvc mockMvc;

    private MessageController messageController;

    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        messageController = new MessageController(messageService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(messageController)
                .build();
    }

    @Test
    @WithMockUser(username = "student@local")
    public void getAllAnnouncements() throws Exception {

        Employee employee = new Employee();
        employee.setFirstName("Jan");
        employee.setLastName("Kowalski");

        Message message = new Message();
        message.setId(0L);
        message.setTitle("Sample Title");
        message.setContent("Sample Content");
        message.setSender(employee);
        message.setTimeStamp(LocalDateTime.now());

        ArrayList<Message> messages = new ArrayList<>();
        messages.add(message);


        Mockito.when(messageRepository.findAll()).thenReturn(messages);

        mockMvc.perform(get("http://localhost:8080/api/announcement?page=0&size=5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content",hasSize(1)));
    }

    @Test
    @WithMockUser(username = "hydraulik")
    public void addAnnouncement() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Jan");
        employee.setLastName("Kowalski");

        Mockito.when(employeeRepository.findByEmail("hydraulik")).thenReturn(employee);

        NewAnnouncement newAnnouncement = new NewAnnouncement("SampleTitle", "SampleContent");

        mockMvc.perform(post("http://localhost:8080/api/announcement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newAnnouncement)))
                .andExpect(status().isOk());

    }
}