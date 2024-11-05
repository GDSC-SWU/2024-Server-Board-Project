package gdgserver.practice.simpleboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false)
    private Integer categoryId;

    @Convert(converter = UserGradeConverter.class)
    @Column(name = "access_grade")
    private UserGrade accessGrade;

    @Column(name = "category_name", nullable = false, length = 30)
    private String categoryName;
}
