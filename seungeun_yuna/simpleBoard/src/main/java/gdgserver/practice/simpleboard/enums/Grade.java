package gdgserver.practice.simpleboard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Grade {
    ADMIN(0L, "ADMIN"),
    D(10L, "userGrade_D"),
    C(20L, "userGrade_C"),
    B(30L, "userGrade_B"),
    A(40L, "userGrade_A");

    private final Long gradeId;
    private final String gradeName;

    public static Grade enumOf(Long gradeId) {
        return Arrays.stream(Grade.values())
                .filter(t->t.getGradeId().equals(gradeId))
                .findAny().orElse(null);
    }
}

