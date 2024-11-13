package gdgserver.practice.simpleboard.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="comment")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //댓글 내용
    @Column(columnDefinition = "TEXT",nullable=false)
    private String content;

    @Column(name="created_date")
    @CreatedDate
    private String createdAt;

    @Column(name="updated_date")
    @LastModifiedDate
    private String updatedAt;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


}
