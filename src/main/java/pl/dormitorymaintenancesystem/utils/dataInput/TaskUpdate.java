package pl.dormitorymaintenancesystem.utils.dataInput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdate {
    private String comment;
    private Integer status;
}
