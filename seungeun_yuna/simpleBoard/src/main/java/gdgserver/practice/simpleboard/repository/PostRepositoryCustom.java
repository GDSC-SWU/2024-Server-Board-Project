package gdgserver.practice.simpleboard.repository;

import gdgserver.practice.simpleboard.domain.Post;

public interface PostRepositoryCustom {
    void updateCount(Post post, boolean increment);
}
