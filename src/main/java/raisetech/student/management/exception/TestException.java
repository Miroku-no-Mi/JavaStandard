package raisetech.student.management.exception;

public class TestException extends RuntimeException {
    public TestException() {
        super();
    }

    //　例外が発生した時に表示されるエラーメッセージ
    public TestException(String message) {
        super(message);
    }

    //　原因例外
    public TestException(String message, Throwable cause) {
        super(message, cause);
    }
    //　エラーメッセージのcauseをつける。
    public TestException(Throwable cause) {
        super(cause);
    }
}
