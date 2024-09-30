package com.example.kpaas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "comment_upvotes")
@Getter
@Setter
@IdClass(UpvotesPK.class)
public class Commentupvote {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment commentId;

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;


}
class UpvotesPK implements Serializable {
    private Long commentId;;

    private String userId;;
}