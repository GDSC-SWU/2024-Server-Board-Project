package me.hakyuwon.miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hakyuwon.miniproject.domain.Board;
import me.hakyuwon.miniproject.domain.Board;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {

    private String title;
    private String content;
    private String imageUrl;

    public Board toEntity(){ //생성자를 사용해 객체 생성
        return Board.builder()
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .build();
    }

}
