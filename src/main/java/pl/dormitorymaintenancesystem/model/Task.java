package pl.dormitorymaintenancesystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.dormitorymaintenancesystem.enums.TaskStatusEnum;
import pl.dormitorymaintenancesystem.model.users.Inhabitant;
import pl.dormitorymaintenancesystem.model.users.Worker;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inhabitant_id")
    private Inhabitant inhabitant;

    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;

    private String title;

    @Column(length = 512)
    private String content;

    @Column(length = 512)
    private String comment;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime timeStamp;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Worker worker;

}

