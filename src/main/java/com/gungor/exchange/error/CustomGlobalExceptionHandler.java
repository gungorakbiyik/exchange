package com.gungor.exchange.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        return createResponseEntity(HttpStatus.NOT_FOUND, "An error occured!");
    }

    @ExceptionHandler({ConversionNotFoundException.class})
    public ResponseEntity<Object> handleConversionNotFoundException(ConversionNotFoundException ex, WebRequest request) {
        return createResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private Map<String, Object> createBodyMap(HttpStatus status, List<String> errors) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status);
        body.put("errors", errors);
        return body;
    }

    private ResponseEntity<Object> createResponseEntity(HttpStatus status, List<String> errors) {
        return new ResponseEntity<>(createBodyMap(status, errors), new HttpHeaders(), status);
    }

    private ResponseEntity<Object> createResponseEntity(HttpStatus status, String... errors) {
        return new ResponseEntity<>(createBodyMap(status, Arrays.asList(errors)), new HttpHeaders(), status);
    }
}
