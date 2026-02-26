package raisetech.student.management.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "エラーレスポンス")
public class ErrorResponse {

    @Schema(description = "エラーメッセージ", example = "該当IDの受講生が存在しません。")
    private String message;

    @Schema(description = "HTTPステータスコード", example = "404")
    private int status;

    public  ErrorResponse(String message, int status){
        this.message = message;
        this.status = status;
    }

    public String getMessage(){
        return message;
    }

    public  int getStatus(){
        return status;
    }
}

