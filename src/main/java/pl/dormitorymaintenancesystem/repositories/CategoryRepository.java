package pl.dormitorymaintenancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dormitorymaintenancesystem.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getTopByCategory(String category);
}
