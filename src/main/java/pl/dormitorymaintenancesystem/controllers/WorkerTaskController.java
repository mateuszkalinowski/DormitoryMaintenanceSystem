package pl.dormitorymaintenancesystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.service.WorkerTaskService;
import pl.dormitorymaintenancesystem.utils.dataInput.TaskUpdateDTO;

@RestController
@RequestMapping("api/worker/task")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorkerTaskController {

    private final WorkerTaskService workerTaskService;

    @GetMapping(value = "/waitingTasks")
    public ResponseEntity getAllWaitingTasks(@RequestParam int page, @RequestParam int size) {
        return workerTaskService.getAllWaitingTasks(page,size);
    }

    @GetMapping(value = "/assignedTasks")
    public ResponseEntity getAllAssignedToMeTasks(@RequestParam int page, @RequestParam int size) {
        return workerTaskService.getAllAssignedToMeTasks(page,size);
    }

    @PostMapping(value = "/{id}/assignToMe")
    public ResponseEntity assignTastToMe(@PathVariable Long id) {
        return workerTaskService.assignTaskToMe(id);
    }

    @GetMapping(value = "/{id}/taskDetails")
    public ResponseEntity taskDetails(@PathVariable Long id) {
        return workerTaskService.taskDetails(id);
    }

    @PostMapping(value = "/{id}/update")
    public ResponseEntity updateTask(@PathVariable Long id, @RequestBody TaskUpdateDTO taskUpdateDTO) {
        return workerTaskService.updateTask(id, taskUpdateDTO);
    }
}