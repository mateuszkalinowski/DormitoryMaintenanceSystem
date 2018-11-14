package pl.dormitorymaintenancesystem.utils.dataInput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerInfo {

    private String contactEmail;
    private String phone;
}
