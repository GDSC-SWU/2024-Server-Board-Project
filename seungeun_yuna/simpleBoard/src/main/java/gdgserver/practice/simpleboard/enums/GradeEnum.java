package gdgserver.practice.simpleboard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum GradeEnum {
    ADMIN(0L, "ADMIN"),
    D(10L, "user_D"),
    C(20L, "user_C"),
    B(30L, "user_B"),
    A(40L, "user_A");

    private final Long gradeId;
    private final String gradeName;

    public static GradeEnum enumOf(String _gradeName) {
        return Arrays.stream(GradeEnum.values())
                .filter(t->t.getGradeName().equals(_gradeName))
                .findAny().orElse(null);
    }
}

