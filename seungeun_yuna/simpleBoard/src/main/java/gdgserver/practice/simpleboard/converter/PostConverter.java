package gdgserver.practice.simpleboard.converter;

import gdgserver.practice.simpleboard.domain.Category;
import gdgserver.practice.simpleboard.domain.Post;
import gdgserver.practice.simpleboard.domain.User;
import gdgserver.practice.simpleboard.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostConverter implements Converter<PostDto.Response, Post> {

    @Override
    public PostDto.Response toDto(Post post) {
        return PostDto.Response.builder()
                .id(post.getId())
                .userId(post.getUser().getId())
                .userName(post.getUser().getNickname())
                .categoryId(post.getCategory().getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

    }

    public Post toEntity(PostDto.Request _request, User _user, Category _category) {
        return Post.builder()
                .title(_request.getTitle())
                .content(_request.getContent())
                .user(_user)
                .category(_category)
                .build();
    }
}
