package kr.or.hanium.probono.littletrio.exception;

/**
 * {@code UnAuthenticationException} 은 로그인하지 않은 사용자와 같이
 * 인증되지 않은 사용자가 요청했을 때 발생시킬 예외처리 클래스입니다.
 *
 * @author Kwon Hyoena
 */
public class UnAuthenticationException extends Exception {
    private static final long serialVersionUID = 1L;

    public UnAuthenticationException() {
        super();
    }

    public UnAuthenticationException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthenticationException(String message) {
        super(message);
    }

    public UnAuthenticationException(Throwable cause) {
        super(cause);
    }
}
