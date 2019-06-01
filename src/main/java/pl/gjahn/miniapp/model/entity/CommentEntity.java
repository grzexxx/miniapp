package pl.gjahn.miniapp.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Data
public class CommentEntity {


    @Id
    @GeneratedValue
    private int id;
    private String comment;

    @Column(name = "creation_date")
    private LocalDateTime creationTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;


    public CommentEntity() {
    }

}



