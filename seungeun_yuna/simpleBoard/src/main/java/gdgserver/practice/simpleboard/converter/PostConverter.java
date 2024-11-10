package gdgserver.practice.simpleboard.converter;

import gdgserver.practice.simpleboard.domain.Post;
import gdgserver.practice.simpleboard.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostConverter implements Converter<PostDto.PostResponseDto, Post> {

    @Override
    public PostDto.PostResponseDto toDto(Post post) {
        return PostDto.PostResponseDto.builder()
                .id(post.getId())
                .userId(post.getUser().getId())
                .userName(post.getUser().getNickname())
                .categoryId(post.getCategory().getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

    }
}
