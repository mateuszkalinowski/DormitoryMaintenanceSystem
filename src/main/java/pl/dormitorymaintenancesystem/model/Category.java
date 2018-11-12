package pl.dormitorymaintenancesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dormitorymaintenancesystem.model.users.Worker;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "Category")
@NoArgsConstructor
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String category;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    List<Task> taskList = new ArrayList<>();

    @ManyToMany(mappedBy = "categories")
    private List<Worker> workers = new ArrayList<>();

    public Category(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = (Category) o;
        return Objects.equals(id, category1.id) &&
                Objects.equals(category, category1.category) &&
                Objects.equals(taskList, category1.taskList) &&
                Objects.equals(workers, category1.workers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, taskList, workers);
    }
}
