package raisetech.student.management.repository;

import org.apache.ibatis.annotations.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.domain.StudentSearchCondition;

import java.util.List;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 *
 */

@Mapper
public interface StudentRepository {
    /**
     * 検索条件に一致する受講生を検索します。
     * @param condition　検索条件（名前・年齢・削除フラグなど）
     * @return 検索条件に一致する受講生一覧
     */
    List<Student> searchByCondition(StudentSearchCondition condition);

    /**
     * 受講生全件検索を行います。
     *
     * @return 受講生一覧（全件）
     */
    @Select("SELECT * FROM students")
    List<Student> search();

    /**
     *
     * @param id 受講生ID
     * @return 受講生
     */
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student searchStudent(String id);

    /**
     * 受講生のコース情報の全件検索を行います。
     * @return 受講生のコース情報（全件）
     */
    @Select("SELECT * FROM students_courses")
    List<StudentsCourses> searchStudentsCoursesList();

    /**
     * 受講生IDに紐づく受講生コース情報を検索します。
     *
     * @param studentId 受講生ID
     * @return 受講生IDに紐づく受講生コース情報
     */
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

