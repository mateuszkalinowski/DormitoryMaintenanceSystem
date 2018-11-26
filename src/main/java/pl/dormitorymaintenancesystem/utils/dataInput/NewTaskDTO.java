package pl.dormitorymaintenancesystem.utils.dataInput;

import lombok.Data;

@Data
public class NewTaskDTO {
    private String title;
    private String category;
    private String description;
}

