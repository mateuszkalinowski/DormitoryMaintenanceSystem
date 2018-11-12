package pl.dormitorymaintenancesystem.model;

import lombok.Data;
import pl.dormitorymaintenancesystem.enums.DormitoryNameEnum;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    private String roomNumber;

    @Enumerated(EnumType.STRING)
    private DormitoryNameEnum dormitoryNameEnum;

    @OneToMany(mappedBy = "room")
    private List<Inhabitant> inhabitantList = new ArrayList<>();
}
