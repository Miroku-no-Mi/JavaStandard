package raisetech.student.management.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentSearchCondition {
    private String name;
    private String kanaName;
    private Integer age;
    private boolean deleted;
}
