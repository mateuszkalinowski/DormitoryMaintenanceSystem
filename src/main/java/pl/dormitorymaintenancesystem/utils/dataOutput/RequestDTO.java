package pl.dormitorymaintenancesystem.utils.dataOutput;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDTO implements Comparable<RequestDTO> {

    private Long id;

    private String title;
    private String content;

    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime time;

    private String comment;

    private String categoryName;

    private String status;

    private String assignedTo;

    public RequestDTO(Long id, String title, String content, LocalDateTime time, String comment, String categoryName, String status, String assignedTo) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
        this.comment = comment;
        this.categoryName = categoryName;
        this.status = status;
        this.assignedTo = assignedTo;
    }

    @Override
    public int compareTo(RequestDTO o) {
        return this.time.compareTo(o.time);
    }
}
