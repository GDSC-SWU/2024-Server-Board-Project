package gdgserver.practice.simpleboard.repository;


import gdgserver.practice.simpleboard.domain.Like;
import gdgserver.practice.simpleboard.domain.Post;
import gdgserver.practice.simpleboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long>{
    Optional<Like> findByUserAndPost(User user, Post post);
}