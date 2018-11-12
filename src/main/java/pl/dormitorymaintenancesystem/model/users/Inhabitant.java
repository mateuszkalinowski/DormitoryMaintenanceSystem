package pl.dormitorymaintenancesystem.model.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dormitorymaintenancesystem.model.Room;
import pl.dormitorymaintenancesystem.model.Task;
import pl.dormitorymaintenancesystem.model.UserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Inhabitant extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "inhabitant")
    List<Task> taskList = new ArrayList<>();

    public Inhabitant(String email, String password, String firstname, String lastname, UserRole role) {
        super(email, password, firstname, lastname, role);
    }

    @ManyToOne
    private Room room;
}
