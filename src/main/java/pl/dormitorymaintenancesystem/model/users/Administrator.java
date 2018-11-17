package pl.dormitorymaintenancesystem.model.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dormitorymaintenancesystem.model.UserRole;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Administrator extends Employee {

    public Administrator(String email, String password, String firstname, String lastname, UserRole role) {
        super(email, password, firstname, lastname, role);
    }
}
