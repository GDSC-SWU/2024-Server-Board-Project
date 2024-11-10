package gdgserver.practice.simpleboard.controller;

import gdgserver.practice.simpleboard.dto.CategoryDto;
import gdgserver.practice.simpleboard.service.CategoryService;
import gdgserver.practice.simpleboard.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class CategoryApiController {
    private final CategoryService categoryService;

    // 게시판 조회
    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<CategoryDto.CategoryResponseDto>>> findAll(){
        List<CategoryDto.CategoryResponseDto> responseList = categoryService.findAll();

        return ResponseEntity.ok().body(ApiResponse.<List<CategoryDto.CategoryResponseDto>>builder()
                .isSuccess(true)
                .code("200 OK")
                .message("요청 성공")
                .result(responseList)
                .build());
    }
}
