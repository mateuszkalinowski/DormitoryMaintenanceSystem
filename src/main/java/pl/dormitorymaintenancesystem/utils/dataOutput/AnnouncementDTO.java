package pl.dormitorymaintenancesystem.utils.dataOutput;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AnnouncementDTO extends Object implements Serializable, Comparable<AnnouncementDTO> {

    private String id;

    private String title;
    private String content;

    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime timeStamp;
    private String workerEmail;
    private String workerFirstName;
    private String workerLastName;

    @Override
    public int compareTo(AnnouncementDTO o) {
        return o.timeStamp.compareTo(this.timeStamp);
    }

    public AnnouncementDTO(String id, String title, String content, LocalDateTime timeStamp, String workerEmail, String workerFirstName, String workerLastName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timeStamp = timeStamp;
        this.workerEmail = workerEmail;
        this.workerFirstName = workerFirstName;
        this.workerLastName = workerLastName;
    }
}
