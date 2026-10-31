package CRUD_PROJECT.SpringBootDemo.exception;

import CRUD_PROJECT.SpringBootDemo.DTOs.exception.ExceptionResponseDto;
import CRUD_PROJECT.SpringBootDemo.DTOs.exception.ValidationResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jshell.Snippet;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationResponseDto> ValidationResponse(MethodArgumentNotValidException e , HttpServletRequest httpServletRequest){

        Map<String , String> feildError = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(
                error -> feildError.put(error.getField() , error.getDefaultMessage())
        );


        ValidationResponseDto validationResponseDto = new ValidationResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validaations failed",
                httpServletRequest.getRequestURI(),
                feildError

        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(validationResponseDto);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> HandleResourceNotFoundException(ResourceNotFoundException e , HttpServletRequest httpServletRequest){

        ExceptionResponseDto exceptionDto = new ExceptionResponseDto();

        exceptionDto.setTimestamp(LocalDateTime.now());
        exceptionDto.setStatusCode(404);
        exceptionDto.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setPath(httpServletRequest.getRequestURI());



        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionDto);
    }


    @ExceptionHandler(DuplicateResouceFoundException.class)
    public ResponseEntity<ExceptionResponseDto> HandleDuplicateFoundException(DuplicateResouceFoundException e , HttpServletRequest httpServletRequest){

        ExceptionResponseDto exceptionDto = new ExceptionResponseDto();

        exceptionDto.setTimestamp(LocalDateTime.now());
        exceptionDto.setStatusCode(409);
        exceptionDto.setError(HttpStatus.CONFLICT.getReasonPhrase());
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setPath(httpServletRequest.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionDto);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDto> HandleRuntimeException(Exception e , HttpServletRequest httpServletRequest){

        ExceptionResponseDto exceptionDto = new ExceptionResponseDto();

        exceptionDto.setTimestamp(LocalDateTime.now());
        exceptionDto.setStatusCode(500);
        exceptionDto.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setPath(httpServletRequest.getRequestURI());


        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionDto);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> HandleGenricException(Exception e , HttpServletRequest httpServletRequest){

        ExceptionResponseDto exceptionDto = new ExceptionResponseDto();

        exceptionDto.setTimestamp(LocalDateTime.now());
        exceptionDto.setStatusCode(500);
        exceptionDto.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setPath(httpServletRequest.getRequestURI());


        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionDto);
    }

}
