package raisetech.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.repository.StudentRepository;
import raisetech.student.management.domain.StudentDetail;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }
    /**
     * 受講生一覧習得
     */
    public List<Student> searchStudentList() {
        return repository.search();
    }
    /**
     * コース一覧習得
     */
    public List<StudentsCourses> searchStudentsCoursesList() {
        return repository.searchStudentsCourses();
    }
    // idを受け取って、一人分のStudentDetailを返す。
    public  StudentDetail searchStudentDetailById(Integer id){
        Student student = repository.searchStudentById(id);
        List<StudentsCourses> courses =repository.searchStudentsCoursesByStudentId(id);
        StudentDetail detail = new StudentDetail();
        detail.setStudent(student);
        detail.setStudentsCourses(courses);

        return detail;
    }

    @Transactional
    public void registerStudent(StudentDetail studentDetail) {
        repository.registerStudent(studentDetail.getStudent());
        // TODO:コース情報登録も行う。
        for (StudentsCourses studentsCourse : studentDetail.getStudentsCourses()) {
            studentsCourse.setStudentId(studentDetail.getStudent().getId());
            studentsCourse.setCourseStartAt(LocalDateTime.now());
            studentsCourse.setCourseEndAt(LocalDateTime.now().plusYears(1));
            repository.registerStudentsCourses(studentsCourse);
        }
    }
    @Transactional
    public void updateStudent(StudentDetail studentDetail){
        repository.updateStudent(studentDetail.getStudent());
    }
}
