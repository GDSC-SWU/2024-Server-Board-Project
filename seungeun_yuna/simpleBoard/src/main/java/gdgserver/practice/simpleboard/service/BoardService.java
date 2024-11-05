package gdgserver.practice.simpleboard.service;

import gdgserver.practice.simpleboard.domain.Post;
import gdgserver.practice.simpleboard.dto.AddPostRequest;
import gdgserver.practice.simpleboard.dto.UpdatePostRequest;
import gdgserver.practice.simpleboard.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final PostRepository postRepository;

    // 게시글 작성
    public Post save(AddPostRequest request) {
        return postRepository.save(request.toEntity());
    }

    // 게시글 전체 조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // 게시글 내용 조회
    public Post findById(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Post not found: " + id));
    }

    // 게시글 삭제
    public void delete(Integer id) {
        postRepository.deleteById(id);
    }

    // 게시글 수정
    @Transactional
    public Post update(Integer id, UpdatePostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Post not found: " + id));

        post.update(request.getTitle(), request.getContent()); // request로 받은 값으로 게시글 수정

        return post;
    }
}
