package pl.dormitorymaintenancesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.enumTranslation.RequestStatusTranslation;
import pl.dormitorymaintenancesystem.enums.TaskStatusEnum;
import pl.dormitorymaintenancesystem.model.Task;
import pl.dormitorymaintenancesystem.model.users.Worker;
import pl.dormitorymaintenancesystem.repositories.TaskRepository;
import pl.dormitorymaintenancesystem.repositories.WorkerRepository;
import pl.dormitorymaintenancesystem.utils.Page;
import pl.dormitorymaintenancesystem.utils.dataInput.TaskUpdate;
import pl.dormitorymaintenancesystem.utils.dataOutput.MessageDTO;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkerTaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private WorkerRepository workerRepository;

    public ResponseEntity getAllWaitingTasks(int page,int size) {
        try {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String currentEmail = authentication.getName();
                Worker worker = workerRepository.findByEmail(currentEmail);
                if (worker == null)
                    return ResponseEntity.badRequest().build();

                List<Task> waitingTasksList = taskRepository.findAllByWorkerIsNullOrderByTimeStampDesc();

                ArrayList<HashMap<String, Object>> resultList = new ArrayList<>();

                waitingTasksList = waitingTasksList.stream().filter(task -> worker.getCategories().contains(task.getCategory())).collect(Collectors.toList());

                waitingTasksList.sort((o1, o2) -> o2.getTimeStamp().compareTo(o1.getTimeStamp()));

                for (Task task : waitingTasksList) {

                    HashMap<String, Object> taskMap = new HashMap<>();
                    taskMap.put("id", task.getId());
                    taskMap.put("title", task.getTitle());
                    taskMap.put("timeStamp", task.getTimeStamp().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
                    taskMap.put("comment", task.getComment());
                    taskMap.put("content", task.getContent());
                    taskMap.put("category", task.getCategory().getCategory());
                    taskMap.put("status", RequestStatusTranslation.translateRequestStatus(task.getStatus()));
                    taskMap.put("senderFirstName",task.getInhabitant().getFirstName());
                    taskMap.put("senderLastName",task.getInhabitant().getLastName());
                    taskMap.put("room",task.getInhabitant().getRoom().getRoomNumber());
                    taskMap.put("dormitoryName",task.getInhabitant().getRoom().getDormitoryNameEnum().name());

                    resultList.add(taskMap);
                }

                return ResponseEntity.ok(Page.createPage(page,size,resultList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity getAllAssignedToMeTasks(int page,int size) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            Worker worker = workerRepository.findByEmail(currentEmail);
            if (worker == null)
                return ResponseEntity.badRequest().build();

            List<Task> waitingTasksList = worker.getTasks();

            ArrayList<HashMap<String, Object>> resultList = new ArrayList<>();

            waitingTasksList.sort((o1, o2) -> o2.getTimeStamp().compareTo(o1.getTimeStamp()));

            for (Task task : waitingTasksList) {

                HashMap<String, Object> taskMap = new HashMap<>();
                taskMap.put("id", task.getId());
                taskMap.put("title", task.getTitle());
                taskMap.put("timeStamp", task.getTimeStamp().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
                taskMap.put("comment", task.getComment());
                taskMap.put("content", task.getContent());
                taskMap.put("category", task.getCategory().getCategory());
                taskMap.put("status", RequestStatusTranslation.translateRequestStatus(task.getStatus()));
                taskMap.put("senderFirstName",task.getInhabitant().getFirstName());
                taskMap.put("senderLastName",task.getInhabitant().getLastName());
                taskMap.put("room",task.getInhabitant().getRoom().getRoomNumber());
                taskMap.put("dormitoryName",task.getInhabitant().getRoom().getDormitoryNameEnum().name());

                resultList.add(taskMap);
            }

            return ResponseEntity.ok(Page.createPage(page,size,resultList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity assignTastToMe(Long id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            Worker worker = workerRepository.findByEmail(currentEmail);
            if (worker == null)
                return ResponseEntity.badRequest().build();

            Task task = taskRepository.findById(id).orElse(null);
            if (task == null)
                return ResponseEntity.badRequest().build();
            task.setWorker(worker);
            worker.getTasks().add(task);
            workerRepository.save(worker);
            taskRepository.save(task);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity taskDetails(Long id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            Worker worker = workerRepository.findByEmail(currentEmail);
            if (worker == null)
                return ResponseEntity.badRequest().build();

            Task task = taskRepository.findById(id).orElse(null);
            if(task==null)
                return ResponseEntity.badRequest().build();
            if(task.getWorker().getId().equals(worker.getId())) {
                HashMap<String, Object> taskMap = new HashMap<>();
                taskMap.put("id", task.getId());
                taskMap.put("title", task.getTitle());
                taskMap.put("timeStamp", task.getTimeStamp().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
                taskMap.put("comment", task.getComment());
                taskMap.put("content", task.getContent());
                taskMap.put("category", task.getCategory().getCategory());
                taskMap.put("status", RequestStatusTranslation.translateRequestStatus(task.getStatus()));
                taskMap.put("senderFirstName",task.getInhabitant().getFirstName());
                taskMap.put("senderLastName",task.getInhabitant().getLastName());
                taskMap.put("room",task.getInhabitant().getRoom().getRoomNumber());
                taskMap.put("dormitoryName",task.getInhabitant().getRoom().getDormitoryNameEnum().name());

                return ResponseEntity.ok().body(taskMap);

            }
            else {
                return ResponseEntity.badRequest().build();
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
    public ResponseEntity updateTask(Long id,TaskUpdate taskUpdate) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            Worker worker = workerRepository.findByEmail(currentEmail);
            if (worker == null)
                return ResponseEntity.badRequest().build();

            if(taskUpdate.getComment().length()>4096)
                return ResponseEntity.badRequest().body(new MessageDTO("Zbyt długi komentarz"));

            Task task = taskRepository.findById(id).orElse(null);
            if(task==null)
                return ResponseEntity.badRequest().build();
            if(task.getWorker().getId().equals(worker.getId())) {
                task.setStatus(TaskStatusEnum.values()[taskUpdate.getStatus()]);
                task.setComment(taskUpdate.getComment());
                taskRepository.save(task);
                return ResponseEntity.ok().build();

            }
            else {
                return ResponseEntity.badRequest().build();
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}