package raisetech.student.management.repository;

import org.apache.ibatis.annotations.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import java.util.List;

@Mapper
public interface StudentRepository {


    @Select("SELECT * FROM students")
    List<Student> search();

    @Select("SELECT * FROM students WHERE id = #{id}")
    Student searchStudent(String id);

    @Select("SELECT * FROM students_courses")
    List<StudentsCourses> searchStudentsCoursesList();

    @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
    List<StudentsCourses> searchStudentsCourses(String studentId);


    @Insert("INSERT INTO students(name, kana_name, nickname, email, area, age, sex, remark, is_deleted)"
        + "VALUES(#{name}, #{kanaName}, #{nickname}, #{email}, #{area}, #{age}, #{sex}, #{remark}, false)")

    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudent(Student student);


    @Insert("INSERT INTO students_courses( student_id, course_name, course_start_at, course_end_at) " +
            "VALUES(#{studentId}, #{courseName}, #{courseStartAt}, #{courseEndAt})")

    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudentsCourses(StudentsCourses studentsCourses);


    @Update(" UPDATE students SET name = #{name}, kana_name = #{kanaName}, nickname = #{nickname}," +
            "email = #{email}, area = #{area}, age = #{age}, sex = #{sex}, remark = #{remark}, is_deleted = #{isDeleted} WHERE id = #{id} ")
    void updateStudent(Student student);

    @Update(" UPDATE students_courses SET course_name = #{courseName} WHERE id = #{id}")
    void updateStudentsCourses(StudentsCourses studentsCourses);


}

