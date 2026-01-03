package raisetech.student.management.repository;

import org.apache.ibatis.annotations.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import java.util.List;

/**
 * 受講生情報を扱うリポジトリ。
 * 全件検索や単一条件での検索、コース情報の検索が行えるクラスです。
 */

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM students WHERE id = #{id}")
    Student searchStudentById(Integer id);

    @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
    List<StudentsCourses> searchStudentsCoursesByStudentId(Integer studentId);

    //削除されていない受講生を全件検索します。

    @Select("SELECT * FROM students")
    List<Student> search();

    @Select("SELECT * FROM students_courses")
    List<StudentsCourses> searchStudentsCourses();


    // 新規登録（INSERT)

    @Insert("INSERT INTO students(name, kana, nickname, email, area, age, sex, remark, is_deleted)"
        + "VALUES(#{name}, #{kana}, #{nickname}, #{email}, #{area}, #{age}, #{sex}, #{remark}, false)")

    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudent(Student student);

    @Insert("INSERT INTO students_courses( student_id, course_name, course_start_at, course_end_at) " +
            "VALUES(#{studentId}, #{courseName}, #{courseStartAt}, #{courseEndAt})")

    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudentsCourses(StudentsCourses studentsCourses);

    @Update("""
        UPDATE students
        SET
        name=#{name},
        kana=#{kana},
        email=#{email},
        area=#{area},
        age=#{age},
        sex=#{sex},
        remark=#{remark}
        WHERE id = #{id}
        """)
    void updateStudent(Student student);


}

