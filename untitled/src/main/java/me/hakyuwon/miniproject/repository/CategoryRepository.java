package me.hakyuwon.miniproject.repository;

import me.hakyuwon.miniproject.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCategoryName(String categoryName);
}
