package gdgserver.practice.simpleboard.converter;

import gdgserver.practice.simpleboard.domain.Category;
import gdgserver.practice.simpleboard.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryConverter implements Converter<CategoryDto.Response, Category>{

    @Override
    public CategoryDto.Response toDto(Category _category) {
        return CategoryDto.Response.builder()
                .id(_category.getId())
                .name(_category.getCategoryName())
                .accessLevel(_category.getAccessLevel().getGradeName())
                .build();
    }
}
