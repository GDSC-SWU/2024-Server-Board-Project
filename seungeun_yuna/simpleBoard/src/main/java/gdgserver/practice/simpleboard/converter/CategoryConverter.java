package gdgserver.practice.simpleboard.converter;

import gdgserver.practice.simpleboard.domain.Category;
import gdgserver.practice.simpleboard.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryConverter implements Converter<CategoryDto.CategoryResponseDto, Category>{

    @Override
    public CategoryDto.CategoryResponseDto toDto(Category category) {
        return CategoryDto.CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getCategoryName())
                .accessLevel(category.getAccessLevel().getGradeName())
                .build();
    }
}
