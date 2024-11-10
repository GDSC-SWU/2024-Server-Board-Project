package gdgserver.practice.simpleboard.service;

import gdgserver.practice.simpleboard.converter.PostConverter;
import gdgserver.practice.simpleboard.domain.Category;
import gdgserver.practice.simpleboard.domain.Post;
import gdgserver.practice.simpleboard.domain.User;
import gdgserver.practice.simpleboard.dto.PostDto;
import gdgserver.practice.simpleboard.repository.CategoryRepository;
import gdgserver.practice.simpleboard.repository.PostRepository;
import gdgserver.practice.simpleboard.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final PostConverter postConverter;

    // 게시글 작성
    public PostDto.PostResponseDto save(PostDto.PostRequestDto request, Long userId, Long categoryId) {

        // id로 유저와 카테고리 찾기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found: " + userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new IllegalArgumentException("Category not found: " + categoryId));

        // requestDto -> Post Entity로 변환
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .category(category)
                .build();

        // post 저장
        postRepository.save(post);

        // Post -> responseDto 변환해서 반환
        return postConverter.toDto(post);
    }

    // 카테고리 내 게시글 목록 조회
    public List<PostDto.PostResponseDto> findPostsByCategory(Long categoryId) {
        // id로 카테고리 찾기
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new IllegalArgumentException("Category not found: " + categoryId));
        // 카테고리로 게시글 리스트 조회
        List<Post> postList = postRepository.findAllByCategory(category);

        // stream() 사용 -> responseDto 리스트로 변환해서 반환
        return postList.stream()
                .map(postConverter::toDto)
                .collect(Collectors.toList());
    }

    // 게시글 내용 조회
    public PostDto.PostResponseDto findPostById(Long id) {
        // id로 게시글 찾기
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found: " + id));

        // Post -> responseDto 로 변환해서 반환
        return postConverter.toDto(post);
    }

    // 게시글 삭제
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    // 게시글 수정
    @Transactional
    public PostDto.PostResponseDto update(Long id, PostDto.PostRequestDto request) {
        // id로 게시글 찾기
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Post not found: " + id));

        post.update(request.getTitle(), request.getContent()); // request로 받은 값으로 게시글 수정

        return postConverter.toDto(post);
    }

}
