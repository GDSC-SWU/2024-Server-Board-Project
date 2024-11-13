package gdgserver.practice.simpleboard.dto;

import gdgserver.practice.simpleboard.domain.Comment;
import gdgserver.practice.simpleboard.domain.Post;
import gdgserver.practice.simpleboard.domain.User;

public class CommentRequestDto {
    private Long id;
    private String nickname; // 사용자 닉네임을 저장할 변수
    private String content;  // 댓글 내용 추가 (필요하다면)

    // Comment 엔티티를 받아 DTO로 변환하는 생성자 추가
    public CommentRequestDto(Comment comment) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname(); // 댓글 작성자의 닉네임을 가져옴
        this.content = comment.getContent();  // 댓글 내용도 가져옴
    }

    // Getter와 Setter 추가 (필요시)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Comment 엔티티로 변환하는 메서드 추가
    public Comment toEntity(Post post, User user) {
        return new Comment(post, user, content); // Comment 생성자 호출
    }
}

