package pl.dormitorymaintenancesystem.utils.dataInput;

import lombok.Data;

@Data
public class NewWorkerData {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String[] categories;
}
