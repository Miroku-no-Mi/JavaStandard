package raisetech.student.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class Application {

    private String name = "Enami Kouji";
    private String age = "37";

    private Map<String, String> student = new HashMap<>(); // studentの初期化

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/studentInfo") // 登録済み Student
    public Object getStudentInfo() {
        if  (student.isEmpty()){
            return "登録された学生情報はありません。";
        }
        return student;
    }

    @PostMapping("/studentInfo") // 新規登録 Student
    public String addStudentInfo(@RequestParam String name, @RequestParam String age) {

        if (student == null) { //　実装の度、ゼロに戻る　"初期化"
            student = new HashMap<>();
        }

        student.put(name, age); //名前と年齢がセットで登録され、表示する
        return name + " " + age + "歳";
    }

    @PutMapping("/studentInfo") // データ更新
    public String updatestudent(@RequestParam String oldName, @RequestParam String newName) {
        if (student == null || !student.containsKey(oldName)) {
            return oldName;
        }

        String age = student.remove(oldName);
        student.put(newName, age);
        return oldName + "→" + newName;

    }
}


//修正