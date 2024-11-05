package gdgserver.practice.simpleboard.domain;

import jakarta.persistence.AttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum UserGrade {
    A("40", "userGrade_A"),
    B("30", "userGrade_B"),
    C("20", "userGrade_C"),
    D("10", "userGrade_D");

    private final String gradeCode;
    private final String gradeName;

    public static UserGrade enumOf(String gradeCode) {
        return Arrays.stream(UserGrade.values())
                .filter(t->t.getGradeCode().equals(gradeCode))
                .findAny().orElse(null);
    }
}

