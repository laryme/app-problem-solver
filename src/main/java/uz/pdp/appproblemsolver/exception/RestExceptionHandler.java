package uz.pdp.appproblemsolver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.ErrorData;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = UsernameOrEmailAlreadyExists.class)
    public ResponseEntity<ApiResult<List<ErrorData>>> exceptionHandler(UsernameOrEmailAlreadyExists ex) {
        return new ResponseEntity<>(
                ApiResult
                        .failResponse(ex.getMessage(), HttpStatus.CONFLICT.value()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResult<List<ErrorData>>> exceptionHandler(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(
                ApiResult
                        .failResponse(
                                ex.getBindingResult().getFieldErrors()
                                        .stream()
                                        .map(e -> new ErrorData(e.getDefaultMessage(), HttpStatus.BAD_REQUEST.value()))
                                        .collect(Collectors.toList())),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResult<?>> exceptionHandler(BadCredentialsException ex) {
        return new ResponseEntity<>(
                ApiResult
                        .failResponse(
                                "Username or password incorrect",
                                HttpStatus.UNAUTHORIZED.value()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ApiResult<?>> exceptionHandler(LockedException ex) {
        return new ResponseEntity<>(
                ApiResult
                        .failResponse(
                                "Account is blocked due to some causes",
                                HttpStatus.UNAUTHORIZED.value()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ApiResult<?>> exceptionHandler(DisabledException ex) {
        return new ResponseEntity<>(
                ApiResult
                        .failResponse(
                                "Account has not been activated yet. Please activate your account",
                                HttpStatus.UNAUTHORIZED.value()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(TokenExpiredOrInvalid.class)
    public ResponseEntity<ApiResult<?>> exceptionHandler(TokenExpiredOrInvalid ex) {
        return new ResponseEntity<>(
                ApiResult
                        .failResponse(
                                "The link you have clicked on is no longer valid. Please request a new link from the sender.",
                                HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST
        );
    }

}
