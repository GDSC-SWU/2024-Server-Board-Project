package me.hakyuwon.miniproject.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", updatable = false)
    private Long likeID;

    @ManyToOne(fetch = FetchType.LAZY) //대댓글
    @JoinColumn(name = "post_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY) //대댓글
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
