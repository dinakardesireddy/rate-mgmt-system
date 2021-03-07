package com.dpworld.rms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ApiError> handleEntityNotFound(
            ResourceNotFoundException exception, WebRequest request) {
        return buildErrorResponse(exception, request,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerException.class)
    protected ResponseEntity<ApiError> handleRunTimeException(
            InternalServerException exception, WebRequest request) {
        return buildErrorResponse(exception, request,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiError> buildErrorResponse(Exception exception, WebRequest request, HttpStatus httpStatus) {
        ApiError apiError = new ApiError(LocalDateTime.now(),
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                exception.getMessage(),
                ((ServletWebRequest) request).getRequest().getServletPath());
        return new ResponseEntity<>(apiError, httpStatus);
    }
}
