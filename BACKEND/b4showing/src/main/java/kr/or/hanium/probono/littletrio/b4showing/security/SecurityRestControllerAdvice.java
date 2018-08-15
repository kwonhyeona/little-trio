package kr.or.hanium.probono.littletrio.b4showing.security;

import kr.or.hanium.probono.littletrio.b4showing.error.ErrorResponse;
import kr.or.hanium.probono.littletrio.b4showing.exception.AlreadyExistentResourceException;
import kr.or.hanium.probono.littletrio.b4showing.exception.NonExistentResourceException;
import kr.or.hanium.probono.littletrio.b4showing.exception.UnAuthenticationException;
import kr.or.hanium.probono.littletrio.b4showing.exception.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class SecurityRestControllerAdvice {

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ErrorResponse> unAuthorized(UnAuthorizedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(UnAuthenticationException.class)
    public ResponseEntity<ErrorResponse> unAuthentication(UnAuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(NonExistentResourceException.class)
    public ResponseEntity<ErrorResponse> NonExistentResource(NonExistentResourceException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(AlreadyExistentResourceException.class)
    public ResponseEntity<ErrorResponse> AlreadyExistentResource(AlreadyExistentResourceException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> ConstraintViolation() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("전송한 값 중 유효하지 않은 값이 포함되어 있습니다."));
    }
}
