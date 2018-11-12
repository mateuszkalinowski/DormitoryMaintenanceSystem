package pl.dormitorymaintenancesystem.utils.dataOutput;

import lombok.Data;

@Data
public class InhabitantDTO {
    String firstName;
    String lastName;
    String email;
    String password;
    String roomNumber;
    String dormitoryName;
}
