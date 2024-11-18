package me.hakyuwon.miniproject.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long userID;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String pw;

    @Column(name = "nickname", nullable = false)
    private String  nickname;

    @Column(name = "post_count")
    private int post_count;

    @Column(name = "comment_count")
    private int comment_count;

    @Column(name = "like_count")
    private int like_count;

    @Column(name = "image")
    private String image;

    @Builder
    public User(String nickname, String email, String password, String auth) {
        this.nickname = nickname;
        this.email = email;
        this.pw = password;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // 댓글 매핑
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // 게시글
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // 좋아요
    private List<Like> likes = new ArrayList<>();

}
