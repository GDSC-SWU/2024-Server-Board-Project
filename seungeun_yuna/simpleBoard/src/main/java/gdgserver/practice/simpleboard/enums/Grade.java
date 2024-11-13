package gdgserver.practice.simpleboard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Grade {
    ADMIN("0", "ADMIN"),
    D("10", "userGrade_D"),
    C("20", "userGrade_C"),
    B("30", "userGrade_B"),
    A("40", "userGrade_A");

    private final String gradeCode;
    private final String gradeName;

    public static Grade enumOf(String gradeCode) {
        return Arrays.stream(Grade.values())
                .filter(t->t.getGradeCode().equals(gradeCode))
                .findAny().orElse(null);
    }
}

