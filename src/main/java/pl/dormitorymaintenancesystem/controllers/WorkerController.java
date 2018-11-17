package pl.dormitorymaintenancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.enumTranslation.UserStatusTranslation;
import pl.dormitorymaintenancesystem.enums.UserRoleEnum;
import pl.dormitorymaintenancesystem.enums.UserStatusEnum;
import pl.dormitorymaintenancesystem.model.Category;
import pl.dormitorymaintenancesystem.model.UserRole;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;
import pl.dormitorymaintenancesystem.model.users.Worker;
import pl.dormitorymaintenancesystem.repositories.CategoryRepository;
import pl.dormitorymaintenancesystem.repositories.WorkerRepository;
import pl.dormitorymaintenancesystem.service.WorkerService;
import pl.dormitorymaintenancesystem.utils.Page;
import pl.dormitorymaintenancesystem.utils.dataInput.NewWorkerData;
import pl.dormitorymaintenancesystem.utils.dataInput.WorkerInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "api/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping(value = "/updateInfo")
    public ResponseEntity updateWorkerInfo(@RequestBody WorkerInfo workerInfo) {
        return workerService.updateWorkerInfo(workerInfo);
    }

    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity registerWorker(@RequestBody NewWorkerData newWorkerData) {
        return workerService.registerWorker(newWorkerData);
    }

    @GetMapping(value = "/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity getAllForAdmin(@RequestParam int page, @RequestParam int size){
        return workerService.getAllForAdmin(page,size);
    }

}
