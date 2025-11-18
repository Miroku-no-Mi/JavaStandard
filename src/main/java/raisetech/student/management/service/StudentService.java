package raisetech.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    //絞り込みをする。年齢が30代の人のみ抽出する。
    public List<Student> searchStudentList() {
        List<Student> allStudents = repository.search();

        //抽出したリストをコントローラーに返す。
        return allStudents.stream()
                .filter(student -> student.getAge() >= 30 && student.getAge() <= 39)
                .toList();
    }


    //絞り込み検索で『Javaコース』のコース情報のみ抽出する。
    public List<StudentsCourses> searchStudentsCoursesList() {

        List<StudentsCourses> allCourses = repository.searchStudentsCourses();

        //抽出したリストをコントローラーに返す。
        return allCourses.stream()
                .filter(course -> "Javaコース".equals(course.getCourseName()))
                .toList();
    }
}


