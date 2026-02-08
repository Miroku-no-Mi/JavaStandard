package raisetech.student.management.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    private  String id;
    private  String name;
    private  String kanaName;
    private  String nickname;
    @NotBlank(message = "emailを入力してください。")
    private  String email;
    private  String area;
    private  int age;
    private  String sex;
    private String remark;
    private boolean isDeleted;
}
