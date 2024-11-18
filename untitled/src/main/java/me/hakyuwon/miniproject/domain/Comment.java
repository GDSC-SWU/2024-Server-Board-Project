package me.hakyuwon.miniproject.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", updatable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)  // board 매핑
    @JoinColumn(name = "post_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY) // user 매핑
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) // 부모 댓글을 참조
    @JoinColumn(name = "parent_id")  // 부모 댓글을 나타내기 위해 외래 키 이름을 변경
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)  // 대댓글 리스트
    private List<Comment> replies = new ArrayList<>();

}
