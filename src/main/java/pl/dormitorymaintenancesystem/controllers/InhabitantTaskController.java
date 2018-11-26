package pl.dormitorymaintenancesystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.service.InhabitantTaskService;
import pl.dormitorymaintenancesystem.utils.dataInput.NewTaskDTO;

import javax.transaction.Transactional;

@RestController
@RequestMapping("api/inhabitant/task")
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InhabitantTaskController {

    private final InhabitantTaskService inhabitantTaskService;

    @PostMapping
    public ResponseEntity addTask(@RequestBody NewTaskDTO newTaskDTO) {
        return inhabitantTaskService.addTask(newTaskDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        return inhabitantTaskService.deleteTask(id);
    }

    @GetMapping
    public ResponseEntity getAllTaskByInhabitant(@RequestParam int page, @RequestParam int size) {
        return inhabitantTaskService.getAllTasksByInhabitant(page,size);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity getTask(@PathVariable Long id){
        return inhabitantTaskService.getTask(id);
    }
}
