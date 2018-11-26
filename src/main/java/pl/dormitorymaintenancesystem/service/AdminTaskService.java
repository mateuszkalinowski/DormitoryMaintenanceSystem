package pl.dormitorymaintenancesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.dormitorymaintenancesystem.enumTranslation.TaskStatusTranslation;
import pl.dormitorymaintenancesystem.model.Task;
import pl.dormitorymaintenancesystem.repositories.TaskRepository;
import pl.dormitorymaintenancesystem.utils.Page;
import pl.dormitorymaintenancesystem.utils.dataOutput.TaskDTO;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class AdminTaskService {

    @Autowired
    private TaskRepository taskRepository;

    public ResponseEntity getAllTasks(String type, int page, int size) {
        try {
            List<Task> taskList = new ArrayList<>();
            if (type.equals("ALL"))
                taskList = taskRepository.findAll();

            List<TaskDTO> taskDTOList = new ArrayList<>();

            for (Task task : taskList) {
                String assignedTo = "";
                if (task.getWorker() != null) {
                    assignedTo = task.getWorker().getFirstName() + " " + task.getWorker().getLastName().substring(0, 1);
                    if (!task.getWorker().getContactEmail().equals("")) {
                        assignedTo += " (" + task.getWorker().getContactEmail() + ")";
                    }
                }
                taskDTOList.add(new TaskDTO(task.getId(), task.getTitle(), task.getContent(), task.getTimeStamp(), task.getComment(), task.getCategory().getCategory(), task.getStatus().name(), assignedTo));
            }
            taskDTOList.sort((o1, o2) -> o2.getTime().compareTo(o1.getTime()));
            return ResponseEntity.ok().body(Page.createPage(page, size, taskDTOList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity getTaskDetails(Long id) {
        try {
            Task task = taskRepository.findById(id).orElse(null);
            if (task == null)
                return ResponseEntity.badRequest().build();

            HashMap<String, Object> taskMap = new HashMap<>();
            taskMap.put("id", task.getId());
            taskMap.put("title", task.getTitle());
            taskMap.put("timeStamp", task.getTimeStamp().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
            taskMap.put("comment", task.getComment());
            taskMap.put("content", task.getContent());
            taskMap.put("category", task.getCategory().getCategory());
            taskMap.put("status", TaskStatusTranslation.translateTaskStatus(task.getStatus()));
            taskMap.put("senderFirstName", task.getInhabitant().getFirstName());
            taskMap.put("senderLastName", task.getInhabitant().getLastName());
            taskMap.put("room", task.getInhabitant().getRoom().getRoomNumber());
            taskMap.put("dormitoryName", task.getInhabitant().getRoom().getDormitoryNameEnum().name());

            return ResponseEntity.ok().body(taskMap);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity deleteTask(Long id) {
        try {
            Task task = taskRepository.findById(id).orElse(null);
            taskRepository.delete(task);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
