package pl.dormitorymaintenancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.enumTranslation.RequestStatusTranslation;
import pl.dormitorymaintenancesystem.model.Task;
import pl.dormitorymaintenancesystem.model.users.Worker;
import pl.dormitorymaintenancesystem.repositories.TaskRepository;
import pl.dormitorymaintenancesystem.repositories.WorkerRepository;
import pl.dormitorymaintenancesystem.utils.Page;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/worker/task")
public class WorkerTaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping(value = "/waitingTasks")
    public ResponseEntity getAllWaitingTasks(@RequestParam int page, @RequestParam int size) {
        try {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String currentEmail = authentication.getName();
                Worker worker = workerRepository.findByEmail(currentEmail);
                if (worker == null)
                    return ResponseEntity.badRequest().build();

                List<Task> waitingTasksList = taskRepository.findAllByWorkerIsNullOrderByTimeStampDesc();

                ArrayList<HashMap<String, Object>> resultList = new ArrayList<>();

                waitingTasksList = waitingTasksList.stream().filter(task -> worker.getCategories().contains(task.getCategory())).collect(Collectors.toList());

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

    @PostMapping(value = "/{id}/assignToMe")
    public ResponseEntity assignTastToMe(@PathVariable Long id) {
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

}