package kr.or.hanium.probono.littletrio.exception;

/**
 * {@code UnAuthorizedException} 은 권한이 부여되지 않은 상태에서 요청했을 때 발생시킬 예외처리 클래스입니다.
 * @author Kwon Hyoena
 */
public class UnAuthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnAuthorizedException() {
        super();
    }

    public UnAuthorizedException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthorizedException(String message) {
        super(message);
    }

    public UnAuthorizedException(Throwable cause) {
        super(cause);
    }
}
