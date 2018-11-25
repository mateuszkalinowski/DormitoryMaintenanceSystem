package pl.dormitorymaintenancesystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.enumTranslation.RequestStatusTranslation;
import pl.dormitorymaintenancesystem.enums.TaskStatusEnum;
import pl.dormitorymaintenancesystem.model.Task;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;
import pl.dormitorymaintenancesystem.model.users.User;
import pl.dormitorymaintenancesystem.repositories.CategoryRepository;
import pl.dormitorymaintenancesystem.repositories.InhabitantRepository;
import pl.dormitorymaintenancesystem.repositories.TaskRepository;
import pl.dormitorymaintenancesystem.repositories.UserRepository;
import pl.dormitorymaintenancesystem.service.InhabitantTaskService;
import pl.dormitorymaintenancesystem.utils.Page;
import pl.dormitorymaintenancesystem.utils.dataInput.NewRequestDTO;
import pl.dormitorymaintenancesystem.utils.dataOutput.RequestDTO;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/inhabitant/task")
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InhabitantTaskController {

    private final InhabitantTaskService inhabitantTaskService;

    @PostMapping
    public ResponseEntity addRequest(@RequestBody NewRequestDTO newRequestDTO) {
        return inhabitantTaskService.addRequest(newRequestDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteRequest(@PathVariable Long id) {
        return inhabitantTaskService.deleteRequest(id);
    }

    @GetMapping
    public ResponseEntity getAllRequestsByInhabitant(@RequestParam int page, @RequestParam int size) {
        return inhabitantTaskService.getAllRequestsByInhabitant(page,size);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity getRequest(@PathVariable Long id){
        return inhabitantTaskService.getRequest(id);
    }
}
