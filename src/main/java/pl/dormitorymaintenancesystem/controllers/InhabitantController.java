package pl.dormitorymaintenancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.dormitorymaintenancesystem.service.InhabitantService;
import pl.dormitorymaintenancesystem.utils.dataOutput.InhabitantDTO;
import javax.transaction.Transactional;


@RestController
@RequestMapping(value = "api/inhabitant")
@Transactional
public class InhabitantController {

    @Autowired
    private InhabitantService inhabitantService;

    @PostMapping(value = "/add")
    public ResponseEntity addInhabitant(@RequestBody InhabitantDTO inhabitantDTO) {
        return inhabitantService.addInhabitant(inhabitantDTO);
    }

    @GetMapping(value = "/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity getAllForAdmin(@RequestParam int page, @RequestParam int size){
        return inhabitantService.getAllForAdmin(page,size);
    }
}
