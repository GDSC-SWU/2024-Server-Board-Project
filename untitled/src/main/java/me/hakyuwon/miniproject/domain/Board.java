package me.hakyuwon.miniproject.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false)
    private Long postId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image_url")
    private String imageUrl;  // 이미지 URL 필드 추가

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  // user 매핑
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")  // category 매핑
    private Category category;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL) // comment 매핑
    private List<Comment> comments;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL) // like 매핑
    private List<Like> likes;

    @Builder
    public Board(String title, String content, String imageUrl, Category category) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public void update(String title, String content, String imageUrl){
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }
}
