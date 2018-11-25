package pl.dormitorymaintenancesystem.utils.dataInput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAnnouncement {

    private String title;
    private String content;

}
