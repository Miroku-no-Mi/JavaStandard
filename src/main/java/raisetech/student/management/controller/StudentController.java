package raisetech.student.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import raisetech.student.management.exception.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.exception.TestException;
import raisetech.student.management.service.StudentService;

import java.util.List;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして受け付けるControllerです。
 */
@Validated
@RestController
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    /**
     * 受講生詳細の一覧検索です。全件検索を行うので、条件指定は行いません。
     *
     * @return 受講生詳細一覧（全件）
     */
    @Operation(summary = "一覧検索",description = "受講生の一覧を検索します。",
        responses = {
            @ApiResponse(responseCode = "200", description = "取得に成功しました。",
                content = @Content(mediaType = "application/json",
                        array = @ArraySchema(
                        schema = @Schema(implementation = StudentDetail.class)))),

            @ApiResponse(responseCode = "500", description = "サーバーエラーです。",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() throws TestException {
        throw new TestException("エラーが発生しました。");
    }

    /**
     * 受講生詳細の検索です。IDに紐づく任意の受講生の情報を習得します。
     *
     * @param id 受講生ID
     * @return 受講生
     */
    @Operation(summary ="詳細検索",description = "IDに紐づく任意の情報を習得します。",
        responses = {
            @ApiResponse(responseCode = "200", description = "取得に成功しました。",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = StudentDetail.class))),

            @ApiResponse(responseCode = "500", description = "サーバーエラーです。",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping("/student/{id}")
    public StudentDetail getStudent(
            @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") String id) {
        return service.searchStudent(id);
    }

    /**
     *受講生詳細の登録を行います。
     * @param studentDetail　受講生詳細
     * @return 実行結果
     */
    @Operation(summary = "受講生登録",description = "受講生を登録します。",
        responses = {
            @ApiResponse(responseCode = "200", description = "更新成功",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = StudentDetail.class))),

            @ApiResponse(responseCode = "400", description = "入力エラー",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),

            @ApiResponse(responseCode = "404", description = "該当IDの受講生が存在しません。",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))})
    @PostMapping("/registerStudent")
    public ResponseEntity<StudentDetail> registerStudent
    (@RequestBody @Valid StudentDetail studentDetail) {
        StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
        return ResponseEntity.ok(responseStudentDetail);
    }

    /**
     * 受講生詳細の更新を行います。キャンセルフラグの更新もここで行います(論理削除）
     *
     * @param studentDetail 受講生詳細
     * @return 実行結果
     */
    @Operation(summary = "受講生の更新",description = "受講生詳細の更新、及びキャンセルフラグの更新を行います。",
        responses = {
            @ApiResponse(responseCode = "200", description = "更新成功",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))),

            @ApiResponse(responseCode = "400", description = "入力エラー",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),

            @ApiResponse(responseCode = "404", description = "対象データが存在しません。",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))})
    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
        service.updateStudent(studentDetail);
        return ResponseEntity.ok("更新処理が成功しました。");
    }


}


