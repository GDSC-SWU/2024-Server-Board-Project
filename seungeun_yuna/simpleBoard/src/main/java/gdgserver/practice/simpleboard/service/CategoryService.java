package gdgserver.practice.simpleboard.service;

import gdgserver.practice.simpleboard.domain.Category;
import gdgserver.practice.simpleboard.dto.CategoryDto;
import gdgserver.practice.simpleboard.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // 카테고리 목록 조회
    public List<CategoryDto.CategoryResponseDto> findAll() {
        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream()
                .map(category -> CategoryDto.CategoryResponseDto.builder()
                        .id(category.getId())
                        .name(category.getCategoryName())
                        .accessLevel(category.getAccessLevel().getGradeName())
                        .build())
                .collect(Collectors.toList());
    }
}
