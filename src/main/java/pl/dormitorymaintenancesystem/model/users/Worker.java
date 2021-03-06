package pl.dormitorymaintenancesystem.model.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dormitorymaintenancesystem.model.Category;
import pl.dormitorymaintenancesystem.model.Task;
import pl.dormitorymaintenancesystem.model.UserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Worker")
@NoArgsConstructor
public class Worker extends Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Worker(String phoneNumber) {
        super.setPhoneNumber(phoneNumber);
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable( name = "Worker_Category",
            joinColumns = { @JoinColumn(name = "fk_user") },
            inverseJoinColumns = { @JoinColumn(name = "fk_category") }
    )
    private List<Category> categories = new ArrayList<>();

    public Worker(String email, String password, String firstname, String lastname, UserRole role, String phoneNumber) {
        super(email, password, firstname, lastname, role);
        super.setPhoneNumber(phoneNumber);
    }

    @OneToMany(mappedBy = "worker")
    private List<Task> tasks = new ArrayList<>();
}
