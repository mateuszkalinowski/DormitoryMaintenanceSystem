package pl.dormitorymaintenancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dormitorymaintenancesystem.service.UtilsService;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "api/utils")
@Transactional
public class UtilsController {

    @Autowired
    private UtilsService utilsService;

    @GetMapping(value = "/dormitoryNames")
    public ResponseEntity getDormitoryNames() {
        return utilsService.getDormitoryNames();
    }

    @GetMapping(value = "/possibleTaskStates")
    public ResponseEntity getPossibleTaskStates() {
        return utilsService.getPossibleTaskStates();
    }

    @GetMapping(value = "/categories")
    public ResponseEntity getAllCategories(){
        return utilsService.getAllCategories();

    }
}
