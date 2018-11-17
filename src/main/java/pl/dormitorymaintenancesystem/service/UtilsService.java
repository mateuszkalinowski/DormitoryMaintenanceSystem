package pl.dormitorymaintenancesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dormitorymaintenancesystem.enumTranslation.RequestStatusTranslation;
import pl.dormitorymaintenancesystem.enums.DormitoryNameEnum;
import pl.dormitorymaintenancesystem.enums.TaskStatusEnum;
import pl.dormitorymaintenancesystem.model.Category;
import pl.dormitorymaintenancesystem.repositories.CategoryRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UtilsService {

    @Autowired
    private CategoryRepository categoryRepository;

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

    public ResponseEntity getPossibleTaskStates() {
        try {
            List<String> tastStates = new ArrayList<>();

            for(TaskStatusEnum taskStatusEnum : TaskStatusEnum.values()) {
                tastStates.add(RequestStatusTranslation.translateRequestStatus(taskStatusEnum));
            }
            return ResponseEntity.ok(tastStates);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity getAllCategories(){

        List<String> categories = new ArrayList<>();

        for(Category category : categoryRepository.findAll()) {
            categories.add(category.getCategory());
        }
        Collections.sort(categories);
        return ResponseEntity.ok().body(categories);
    }
}
