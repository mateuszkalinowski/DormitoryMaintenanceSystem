package pl.dormitorymaintenancesystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dormitorymaintenancesystem.enums.DormitoryNameEnum;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/utils")
@Transactional
public class UtilsController {

    @GetMapping(value = "/dormitoryNames")
    public ResponseEntity getDormitoryNames() {
        try {
            List<String> dormitoryNames = new ArrayList<>();

            for(DormitoryNameEnum dormitoryNameEnum : DormitoryNameEnum.values()) {
                dormitoryNames.add(dormitoryNameEnum.name());
            }

            return ResponseEntity.ok(dormitoryNames);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
