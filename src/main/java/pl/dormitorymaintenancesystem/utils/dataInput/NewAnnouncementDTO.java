package pl.dormitorymaintenancesystem.utils.dataInput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAnnouncementDTO {

    private String title;
    private String content;

}
