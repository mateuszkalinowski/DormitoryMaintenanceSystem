package pl.dormitorymaintenancesystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.dormitorymaintenancesystem.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "ROLES")
public class UserRole {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRole;

    public UserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole role = (UserRole) o;
        return Objects.equals(id, role.id) &&
                userRole == role.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userRole);
    }
}
