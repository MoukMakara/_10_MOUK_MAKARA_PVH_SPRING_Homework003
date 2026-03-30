package org.ksga._10_mouk_makara_pvh_spring_homework003.exception;//package org.ksga._10_mouk_makara_pvh_spring_homework003.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setType(URI.create("http://localhost:8080/errors/not-found"));
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    // Handle conflict errors
    @ExceptionHandler(ConflictException.class)
    public ProblemDetail handleConflictException(ConflictException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    // Handle validation errors (handle field @Valid in controller)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("timestamp", Instant.now());

        // Collect field validation errors
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        // Add errors to ProblemDetail as extra properties
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }


    // Handle validation errors (handle pagination @Positive in controller)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidationException(HandlerMethodValidationException e) {
        Map<String, String> errors = new HashMap<>();

        // Loop through each invalid parameter validation result
        e.getParameterValidationResults().forEach(parameterError -> {
            String paramName = parameterError.getMethodParameter().getParameterName(); // Get parameter name

            // Loop through each validation error message for this parameter
            for (var messageError : parameterError.getResolvableErrors()) {
                errors.put(paramName, messageError.getDefaultMessage()); // Store error message
            }
        });

        // Create structured ProblemDetail response
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Method Parameter Validation Failed");
        problemDetail.setProperties(Map.of(
                "timestamp", LocalDateTime.now(),
                "errors", errors // Attach validation errors
        ));

        return problemDetail;
    }
}
