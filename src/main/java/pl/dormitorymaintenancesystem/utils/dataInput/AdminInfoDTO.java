package pl.dormitorymaintenancesystem.utils.dataInput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminInfoDTO {

    private String firstname;
    private String lastname;
    private String contactEmail;
    private String phone;
}
