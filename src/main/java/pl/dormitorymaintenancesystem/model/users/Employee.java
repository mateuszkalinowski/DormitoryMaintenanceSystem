package pl.dormitorymaintenancesystem.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dormitorymaintenancesystem.model.Announcement;
import pl.dormitorymaintenancesystem.model.UserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@Entity
public class Employee extends User {

    public Employee(String email, String password, String firstname, String lastname, UserRole role) {
        super(email, password, firstname, lastname, role);
    }

    private String contactEmail;
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    List<Announcement> announcementList = new ArrayList<>();
}
