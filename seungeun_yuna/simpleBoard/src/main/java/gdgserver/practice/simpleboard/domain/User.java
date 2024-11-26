package gdgserver.practice.simpleboard.domain;

import gdgserver.practice.simpleboard.enums.GradeEnum;
import gdgserver.practice.simpleboard.converter.GradeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Convert(converter = GradeConverter.class)
    @Column(name = "grade", nullable = false)
    private GradeEnum grade;

    @Column(name = "profile_img")
    private String imgUrl;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "post_count")
    private int postCount;

    @Column(name = "comment_count")
    private int commentCount;

    @Column(name = "like_count")
    private int likeCount;

    // Post의 FK
    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
