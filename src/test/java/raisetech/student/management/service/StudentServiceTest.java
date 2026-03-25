package raisetech.student.management.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentConverter converter;

    private  StudentService sut;

    @BeforeEach
    void befor(){
        sut = new StudentService(repository, converter);
    }

    @Test
    void 受講生詳細の一覧検索_リポジトリとコンバーターが適切に呼び出せていること() {
        List<Student> studentList = new ArrayList<>();
        List<StudentCourse> studentCourseList = new ArrayList<>();
        when(repository.search()).thenReturn(studentList);
        when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

        sut.searchStudentList();

        verify(repository,times(1)).search();
        verify(repository,times(1)).searchStudentCourseList();
        verify(converter,times(1)).convertStudentDetails(studentList,studentCourseList);
    }

    @Test
    void 受講生詳細検索＿リポジトリが適切に呼び出されていること(){
        Student student = new Student();
        student.setId("1");

        List<StudentCourse> courseList = new ArrayList<>();
        when(repository.searchStudent("1")).thenReturn(student);
        when(repository.searchStudentCourse("1")).thenReturn(courseList);

        sut.searchStudent("1");

        verify(repository,times(1)).searchStudent("1");
        verify(repository,times(1)).searchStudentCourse("1");

    }

    @Test
    void 受講生詳細の登録_リポジトリが適切に呼び出されていること(){
        Student student = new Student();
        student.setId("1");

        StudentCourse course = new StudentCourse();

        List<StudentCourse> courseList = new ArrayList<>();
        courseList.add(course);

        StudentDetail detail = new StudentDetail(student, courseList);

        sut.registerStudent(detail);

        verify(repository,times(1)).registerStudent(student);
        verify(repository, times(1)).registerStudentCourse(course);
    }

    @Test
    void 受講生コースの初期化が正しく行われること() {

        Student student = new Student();
        student.setId("1");

        StudentCourse course = new StudentCourse();

        List<StudentCourse> courseList = new ArrayList<>();
        courseList.add(course);

        StudentDetail detail = new StudentDetail(student, courseList);

        sut.registerStudent(detail);

        // ★ここがポイント（状態を確認）
        assertEquals("1", course.getStudentId());
        assertNotNull(course.getCourseStartAt());
        assertNotNull(course.getCourseEndAt());
    }

    @Test
    void 受講生詳細の更新_リポジトリが適切に呼び出されていること() {

        Student student = new Student();
        student.setId("1");

        StudentCourse course = new StudentCourse();

        List<StudentCourse> courseList = new ArrayList<>();
        courseList.add(course);

        StudentDetail detail = new StudentDetail(student, courseList);

        sut.updateStudent(detail);

        verify(repository, times(1)).updateStudent(student);
        verify(repository, times(1)).updateStudentCourse(course);
    }
}