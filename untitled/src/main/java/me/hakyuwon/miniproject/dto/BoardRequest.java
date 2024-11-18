package me.hakyuwon.miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hakyuwon.miniproject.domain.Board;
import me.hakyuwon.miniproject.domain.Board;
import me.hakyuwon.miniproject.domain.Category;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {

    private String title;
    private String content;
    private String imageUrl;
    private Category category; // String으로 안 들어가도 되는지 의문

    public Board toEntity(){ //생성자를 사용해 객체 생성
        return Board.builder()
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .category(category)
                .build();
    }

}
