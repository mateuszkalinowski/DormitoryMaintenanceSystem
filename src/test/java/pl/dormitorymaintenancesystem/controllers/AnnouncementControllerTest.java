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
import pl.dormitorymaintenancesystem.model.Announcement;
import pl.dormitorymaintenancesystem.model.users.Employee;
import pl.dormitorymaintenancesystem.repositories.EmployeeRepository;
import pl.dormitorymaintenancesystem.repositories.AnnouncementRepository;
import pl.dormitorymaintenancesystem.service.AnnouncementService;
import pl.dormitorymaintenancesystem.utils.dataInput.NewAnnouncementDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class AnnouncementControllerTest {

    private MockMvc mockMvc;

    private AnnouncementController announcementController;

    @InjectMocks
    private AnnouncementService announcementService;

    @Mock
    private AnnouncementRepository announcementRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        announcementController = new AnnouncementController(announcementService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(announcementController)
                .build();
    }

    @Test
    @WithMockUser(username = "student@local")
    public void getAllAnnouncements() throws Exception {

        Employee employee = new Employee();
        employee.setFirstName("Jan");
        employee.setLastName("Kowalski");

        Announcement announcement = new Announcement();
        announcement.setId(0L);
        announcement.setTitle("Sample Title");
        announcement.setContent("Sample Content");
        announcement.setSender(employee);
        announcement.setTimeStamp(LocalDateTime.now());

        ArrayList<Announcement> announcements = new ArrayList<>();
        announcements.add(announcement);


        Mockito.when(announcementRepository.findAll()).thenReturn(announcements);

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

        NewAnnouncementDTO newAnnouncementDTO = new NewAnnouncementDTO("SampleTitle", "SampleContent");

        mockMvc.perform(post("http://localhost:8080/api/announcement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newAnnouncementDTO)))
                .andExpect(status().isOk());

    }
}