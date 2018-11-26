package pl.dormitorymaintenancesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.dormitorymaintenancesystem.enumTranslation.TaskStatusTranslation;
import pl.dormitorymaintenancesystem.enums.TaskStatusEnum;
import pl.dormitorymaintenancesystem.model.Task;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;
import pl.dormitorymaintenancesystem.model.users.User;
import pl.dormitorymaintenancesystem.repositories.CategoryRepository;
import pl.dormitorymaintenancesystem.repositories.InhabitantRepository;
import pl.dormitorymaintenancesystem.repositories.TaskRepository;
import pl.dormitorymaintenancesystem.repositories.UserRepository;
import pl.dormitorymaintenancesystem.utils.Page;
import pl.dormitorymaintenancesystem.utils.dataInput.NewTaskDTO;
import pl.dormitorymaintenancesystem.utils.dataOutput.TaskDTO;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class InhabitantTaskService {

    @Autowired
    private InhabitantRepository inhabitantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity addTask(NewTaskDTO newTaskDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            Inhabitant inhabitant = inhabitantRepository.findByEmail(currentEmail);
            if(inhabitant==null)
                return ResponseEntity.badRequest().build();
            Task task = new Task();
            task.setInhabitant(inhabitant);
            task.setCategory(categoryRepository.getTopByCategory(newTaskDTO.getCategory()));
            task.setStatus(TaskStatusEnum.TASK_WAITING);
            task.setContent(newTaskDTO.getDescription());
            task.setTitle(newTaskDTO.getTitle());
            task.setComment("");
            task.setTimeStamp(LocalDateTime.now());

            taskRepository.save(task);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    public ResponseEntity deleteTask(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();
        User inhabitant = userRepository.findByEmail(currentEmail);
        if(inhabitant==null)
            return ResponseEntity.badRequest().build();

        Task task = taskRepository.findById(id).orElse(null);

        if(task.getInhabitant().equals(inhabitant)) {
            taskRepository.delete(task);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.badRequest().build();

    }

    public ResponseEntity getAllTasksByInhabitant(int page, int size) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            Inhabitant inhabitant = inhabitantRepository.findByEmail(currentEmail);
            if (inhabitant == null)
                return ResponseEntity.badRequest().build();

            List<TaskDTO> taskDTOList = new ArrayList<>();

            for (Task task : inhabitant.getTaskList()) {
                String assignedTo = "";
                if(task.getWorker()!=null) {
                    assignedTo = task.getWorker().getFirstName() + " " + task.getWorker().getLastName().substring(0, 1);
                    if(!task.getWorker().getContactEmail().equals("")) {
                        assignedTo += " (" + task.getWorker().getContactEmail() + ")";
                    }
                }
                taskDTOList.add(new TaskDTO(task.getId(), task.getTitle(), task.getContent(), task.getTimeStamp(), task.getComment(), task.getCategory().getCategory(), task.getStatus().name(),assignedTo));
            }

            taskDTOList.sort((o1, o2) -> o2.getTime().compareTo(o1.getTime()));

            return ResponseEntity.ok().body(Page.createPage(page, size, taskDTOList));


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    public ResponseEntity getTask(Long id){

        Task task = taskRepository.findById(id).orElse(null);
        HashMap<String,Object> taskMap = new HashMap<>();
        taskMap.put("id",task.getId());
        taskMap.put("title",task.getTitle());
        taskMap.put("timeStamp",task.getTimeStamp().format(DateTimeFormatter.ofPattern("mm:hh dd/MM/yyyy")));
        taskMap.put("comment",task.getComment());
        taskMap.put("content",task.getContent());
        taskMap.put("category",task.getCategory().getCategory());
        taskMap.put("status", TaskStatusTranslation.translateTaskStatus(task.getStatus()));
        String assignedTo = "";
        if(task.getWorker()!=null)
            assignedTo = task.getWorker().getContactEmail();
        taskMap.put("assignedTo",assignedTo);

        return ResponseEntity.ok(taskMap);
    }
}
