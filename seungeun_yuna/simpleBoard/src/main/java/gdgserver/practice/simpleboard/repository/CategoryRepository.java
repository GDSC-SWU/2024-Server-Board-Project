package gdgserver.practice.simpleboard.repository;

import gdgserver.practice.simpleboard.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
