package raisetech.student.management.repository;

import org.apache.ibatis.annotations.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import java.util.List;

/**
 * 受講生情報を扱うリポジトリ。
 *
 * 全件検索や単一条件での検索、コース情報の検索が行えるクラスです。
 */

@Mapper
public interface StudentRepository {

    /**
     * 削除されていない受講生を全件検索します。
     */

    @Select("SELECT * FROM students")
    List<Student> search();

    @Select("SELECT * FROM students_courses")
    List<StudentsCourses> searchStudentsCourses();

    /**
     *  新規登録（INSERT)
     */
    @Insert("""
            INSERT INTO students
            (id, name, kana, email, is_deleted)
            VALUES
            (#{id},#{name},#{kana},#{email},#{isDeleted})
            """)
    void insert (Student student);

    @Insert("""
            INSERT INTO students_courses
            (id, student_id, course_name, course_start_at, course_end_at)
            VALUES
            (#{id}, #{studentId}, #{courseName}, #{courseStartAt}, #{courseEndAt})
            """)
            void insertStudentsCourse(StudentsCourses course);

    /**
     *  データ更新 (UPDATE)
     *  remark と is_deleted を更新できる
     */
    @Update("UPDATE students SET " +
        "name = #{name}, " +
        "kana = #{kana}, " +
        "nickname = #{nickname}, " +
        "email = #{email}, " +
        "area = #{area}, " +
        "age = #{age}, " +
        "sex = #{sex}, " +
        "remark = #{remark}," +
        "is_deleted = #{isDeleted} " +
        "WHERE id = #{id}" )
    void update(Student student);

    @Update("UPDATE students SET is_deleted = 1 WHERE id = #{id}")
    void deleteStudent(String id);
}

