package kr.or.hanium.probono.littletrio.b4showing.exception;

/**
 * {@code AlreadyExistentResourceException} 은 이미 존재하는 자원에 대해
 * 또 다시 같은 요청이 들어올 때 발생시킬 예외처리 클래스입니다.
 *
 * @author Kwon Hyoena
 */
public class AlreadyExistentResourceException extends RuntimeException {

    public AlreadyExistentResourceException() {
        super();
    }

    public AlreadyExistentResourceException(String message) {
        super(message);
    }
}
