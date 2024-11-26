package com.example.board.domain;


import com.example.board.domain.enums.Grade;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
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

    @Builder
    public User(String email, String password, String nickname, String auth) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}