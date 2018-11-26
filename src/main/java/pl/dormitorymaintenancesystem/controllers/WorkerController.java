package pl.dormitorymaintenancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.service.WorkerService;
import pl.dormitorymaintenancesystem.utils.dataInput.NewWorkerDataDTO;
import pl.dormitorymaintenancesystem.utils.dataInput.WorkerInfoDTO;

@RestController
@RequestMapping(value = "api/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping(value = "/updateInfo")
    public ResponseEntity updateWorkerInfo(@RequestBody WorkerInfoDTO workerInfoDTO) {
        return workerService.updateWorkerInfo(workerInfoDTO);
    }

    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity registerWorker(@RequestBody NewWorkerDataDTO newWorkerDataDTO) {
        return workerService.registerWorker(newWorkerDataDTO);
    }

    @GetMapping(value = "/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity getAllForAdmin(@RequestParam int page, @RequestParam int size){
        return workerService.getAllForAdmin(page,size);
    }

}
