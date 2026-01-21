package raisetech.student.management.data;

import lombok.Getter;
import lombok.Setter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import java.util.List;

@Getter
@Setter
public class Student {

    private  String id;
    private  String name;
    private  String kanaName;
    private  String nickname;
    private  String email;
    private  String area;
    private  int age;
    private  String sex;
    private String remark;
    private boolean isDeleted;
}
