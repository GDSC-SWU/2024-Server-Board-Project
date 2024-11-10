package gdgserver.practice.simpleboard.domain;

import gdgserver.practice.simpleboard.enums.GradeEnum;
import gdgserver.practice.simpleboard.converter.GradeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false)
    private Long id;

    @Column(name = "category_name", nullable = false, length = 30)
    private String categoryName;

    @Convert(converter = GradeConverter.class)
    @Column(name = "access_level")
    private GradeEnum accessLevel;

    // 연관관계 매핑
    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();

}
