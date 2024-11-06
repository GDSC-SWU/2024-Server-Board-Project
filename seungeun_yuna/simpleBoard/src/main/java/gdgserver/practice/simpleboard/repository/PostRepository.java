package gdgserver.practice.simpleboard.repository;

import gdgserver.practice.simpleboard.domain.Category;
import gdgserver.practice.simpleboard.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // db에서 카테고리로 게시글 조회
    List<Post> findAllByCategory(Category category);
}
