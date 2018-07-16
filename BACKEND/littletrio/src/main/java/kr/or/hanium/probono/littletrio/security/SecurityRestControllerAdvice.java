package kr.or.hanium.probono.littletrio.security;

import kr.or.hanium.probono.littletrio.domain.Result;
import kr.or.hanium.probono.littletrio.exception.NonExistentResourceException;
import kr.or.hanium.probono.littletrio.exception.UnAuthenticationException;
import kr.or.hanium.probono.littletrio.exception.UnAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class SecurityRestControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(SecurityRestControllerAdvice.class);

    @ExceptionHandler(NonExistentResourceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result NonExistentResource() {
        log.debug("NonExistentResourceException is happened!");
        return Result.of().setMessage("잘못된 파라미터로 요청하셨습니다.");
    }
}
