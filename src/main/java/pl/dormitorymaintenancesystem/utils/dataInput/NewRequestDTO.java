package pl.dormitorymaintenancesystem.utils.dataInput;

import lombok.Data;

@Data
public class NewRequestDTO {
    private String title;
    private String category;
    private String description;
}

