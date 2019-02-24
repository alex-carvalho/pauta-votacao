package br.com.ac.pauta.config;

import br.com.ac.pauta.api.v1.response.ErrorResponse;
import br.com.ac.pauta.exception.HttpException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ServerWebInputException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @author Alex Carvalho
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public ResponseEntity handleException(Throwable throwable) {
        this.log.error(throwable.getMessage(), throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler
    public ResponseEntity handleException(HttpException throwable) {
        this.log.error(throwable.getMessage(), throwable);
        return ResponseEntity.status(throwable.getHttpStatus())
                .body(new ErrorResponse(throwable.getMessage(), null));
    }

    @ExceptionHandler
    public ResponseEntity handlerServerInputException(ServerWebInputException e) {
        this.log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(new ErrorResponse("Falta um parâmetro.", e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        this.log.error(e.getMessage(), e);
        String mensagem = String.format("Argumento '%s' deve ser válido '%s' mas é '%s'.",
                e.getName(), e.getRequiredType().getSimpleName(), e.getValue());
        return new ResponseEntity<>(new ErrorResponse(mensagem, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleJsonProcessingException(JsonProcessingException e) {
        this.log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorResponse("JSON de entrada inválido.", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {
        this.log.error(e.getMessage(), e);
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse(e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(message, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}