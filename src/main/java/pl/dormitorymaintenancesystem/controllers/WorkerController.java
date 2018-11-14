package pl.dormitorymaintenancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dormitorymaintenancesystem.model.users.Worker;
import pl.dormitorymaintenancesystem.repositories.WorkerRepository;
import pl.dormitorymaintenancesystem.utils.dataInput.WorkerInfo;

@RestController
@RequestMapping(value = "api/worker")
public class WorkerController {

    @Autowired
    private WorkerRepository workerRepository;

    @PostMapping(value = "/updateInfo")
    public ResponseEntity updateWorkerInfo(@RequestBody WorkerInfo workerInfo) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = authentication.getName();
            Worker worker = workerRepository.findByEmail(currentEmail);
            if (worker == null)
                return ResponseEntity.badRequest().build();

            if(workerInfo.getContactEmail().length()<255 && workerInfo.getPhone().length()<255) {
                worker.setContactEmail(workerInfo.getContactEmail());
                worker.setPhoneNumber(workerInfo.getPhone());
                workerRepository.save(worker);
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
