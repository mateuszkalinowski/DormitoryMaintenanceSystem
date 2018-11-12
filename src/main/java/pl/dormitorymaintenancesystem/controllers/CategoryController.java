package pl.dormitorymaintenancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dormitorymaintenancesystem.model.Category;
import pl.dormitorymaintenancesystem.repositories.CategoryRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "api/categories")
@Transactional
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity getAllCategories(){

        List<String> categories = new ArrayList<>();

        for(Category category : categoryRepository.findAll()) {
            categories.add(category.getCategory());
        }
        Collections.sort(categories);
        return ResponseEntity.ok().body(categories);
    }
}
