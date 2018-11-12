package pl.dormitorymaintenancesystem.model.users;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Administrator extends User {
    @Id
    @GeneratedValue
    private Long id;

    private String contactEmail;
    private String phoneNumber;
}
