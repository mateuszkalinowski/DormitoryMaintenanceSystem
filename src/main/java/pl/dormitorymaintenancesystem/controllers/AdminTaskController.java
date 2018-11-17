package pl.dormitorymaintenancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.service.AdminTaskService;


@RestController
@RequestMapping("api/admin/task")
public class AdminTaskController {

    @Autowired
    private AdminTaskService adminTaskService;

    @GetMapping("/{type}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity getAllTasks(@PathVariable String type, @RequestParam int page, @RequestParam int size) {
        return adminTaskService.getAllTasks(type, page, size);
    }

    @GetMapping(value = "/{id}/taskDetails")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity taskDetails(@PathVariable Long id) {

        return adminTaskService.getTaskDetails(id);
    }

    @DeleteMapping(value = "/{id}/deleteTask")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        return adminTaskService.deleteTask(id);
    }
}
