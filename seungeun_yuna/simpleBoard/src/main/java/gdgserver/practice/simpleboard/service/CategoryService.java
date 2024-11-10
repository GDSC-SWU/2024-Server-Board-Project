package gdgserver.practice.simpleboard.service;

import gdgserver.practice.simpleboard.converter.CategoryConverter;
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
    private final CategoryConverter categoryConverter;

    // 카테고리 목록 조회
    public List<CategoryDto.CategoryResponseDto> findAll() {
        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream()
                .map(categoryConverter::toDto)
                .collect(Collectors.toList());
    }
}
