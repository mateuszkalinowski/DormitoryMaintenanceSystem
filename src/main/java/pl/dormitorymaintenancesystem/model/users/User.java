package pl.dormitorymaintenancesystem.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.dormitorymaintenancesystem.enums.UserStatusEnum;
import pl.dormitorymaintenancesystem.model.UserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;

    public User(){}

    public User(String email, String password, String firstname, String lastname, UserRole role) {
        this.email = email;
        this.password = password;
        this.firstName = firstname;
        this.lastName = lastname;
        userRoles.add(role);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<UserRole> userRoles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserStatusEnum userStatus;
}
