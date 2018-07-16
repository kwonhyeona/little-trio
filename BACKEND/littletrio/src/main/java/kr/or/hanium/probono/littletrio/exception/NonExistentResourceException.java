package kr.or.hanium.probono.littletrio.exception;

/**
 * {@code NonExistentResourceException} 은 존재하지 않는 자원에 대한
 * 요청이 들어올 때 발생시킬 예외처리 클래스입니다.
 *
 * @author Kwon Hyoena
 */
public class NonExistentResourceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NonExistentResourceException() {
        super();
    }

    public NonExistentResourceException(String message) {
        super(message);
    }

    public NonExistentResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
