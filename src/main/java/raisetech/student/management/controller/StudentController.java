package raisetech.student.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.service.StudentService;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students") //APIの共通パス
public class StudentController {

    private final StudentService service;
    private final StudentConverter converter;

    public StudentController(StudentService service, StudentConverter converter) {
        this.service = service;
        this.converter = converter;
    }
    /** 受講性 + コース一覧取得  */
    @GetMapping("/details")
    public List<StudentDetail> getStudentList() {
        List<Student> students = service.searchStudentList();
        List<StudentsCourses> studentsCourses = service.searchStudentsCoursesList();
        return converter.convertStudentDetails(students, studentsCourses);
    }
    /** コースのみ一覧 */
    @GetMapping("/Courses")
    public List<StudentsCourses> getStudentsCoursesList() {
        return service.searchStudentsCoursesList();
    }

    /** 受講生の論理削除 */
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id){
        service.deleteStudent(id);
    }
}
