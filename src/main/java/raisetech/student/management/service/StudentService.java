package raisetech.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.repository.StudentRepository;

import java.util.List;

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
}

