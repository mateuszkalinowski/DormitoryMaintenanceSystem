package pl.dormitorymaintenancesystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pl.dormitorymaintenancesystem.model.users.Worker;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Message implements Serializable, Comparable<Message> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 512)
    private String content;

    @CreationTimestamp
    private LocalDateTime timeStamp;

    @ManyToOne
    @JoinColumn(name="sender_id")
    private Worker sender;

    @Override
    public int compareTo(Message o) {
        return this.timeStamp.compareTo(o.timeStamp);
    }

    public Message(String title, String content, Worker sender) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.timeStamp = LocalDateTime.now();
    }
}
