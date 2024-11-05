package com.example.board.domain;


import com.example.board.domain.enums.Grade;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    private String nickname;

    @Column(nullable = true)
    private Integer postCount;

    @Column(nullable = true)
    private Integer commentCount;

    @Column(nullable = true)
    private Integer likeCount;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(nullable = true)
    private String image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Article> articles = new ArrayList<>();  // 게시글 양방향 매핑

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();  // 댓글 양방향 매핑

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLike> likes = new ArrayList<>();  // 좋아요 양방향 매핑
}
