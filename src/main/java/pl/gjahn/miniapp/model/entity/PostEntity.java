package pl.gjahn.miniapp.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "post")
@Data
public class PostEntity {


    @Id
    @GeneratedValue
    private int id;
    @Column(name = "post_title")
    private String postName;
    @Column(name = "post_text")
    private String postText;

    @Column(name = "creation_date")
    private LocalDateTime creationTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;


}

