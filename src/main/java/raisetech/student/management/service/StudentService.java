package raisetech.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.repository.StudentRepository;
import raisetech.student.management.domain.StudentDetail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    /** 受講生一覧習得 */
    public List<Student> searchStudentList() {
        return repository.search();
    }

    /** コース一覧習得 */

    public List<StudentsCourses> searchStudentsCoursesList() {
        return repository.searchStudentsCourses();
    }

    /** 受講生を新規登録 */
    public  void insertStudent(Student student){
        repository.insert(student);
    }

    /** 受講生情報を更新 */
    public void updateStudent(Student student) {
        repository.update(student);
    }

    /** 受講生を論理削除（is_deleted = 1 にする） */
    public void deleteStudent(String id) {
        repository.deleteStudent(id);
    }
    // 複合登録
    @Transactional
    public void registerStudentWithCourse(StudentDetail studentDetail) {

        if (studentDetail.getStudent() ==null){
            studentDetail.setStudent(new Student());
        }

        if (studentDetail.getStudentsCourses() ==null
                || studentDetail.getStudentsCourses().isEmpty()){

            StudentsCourses course = new StudentsCourses();
            course.setCourseName("未設定コース");

            List<StudentsCourses> list = new ArrayList<>();
            list.add(course);
            studentDetail.setStudentsCourses(list);
        }
        Student student = studentDetail.getStudent();
        student.setId(UUID.randomUUID().toString());
        student.setDeleted(false);
        student.setKana(" ");
        student.setEmail("dummy@example.com");

        repository.insert(student);

        for (StudentsCourses courses : studentDetail.getStudentsCourses()){

            courses.setId(UUID.randomUUID().toString());

            courses.setStudentId(student.getId());
            courses.setCourseStartAt(LocalDateTime.now());
            courses.setCourseEndAt(LocalDateTime.now().plusYears(1));
            repository.insertStudentsCourse(courses);
        }
    }
}
