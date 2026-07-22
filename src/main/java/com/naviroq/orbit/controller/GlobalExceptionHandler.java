package com.naviroq.orbit.controller;

import com.naviroq.orbit.domain.dto.ErrorDto;
import com.naviroq.orbit.exception.OrbitNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream().findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Input Validation Error");
        ErrorDto errorDto = new ErrorDto(errorMessage);

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrbitNotFoundException.class)
    public ResponseEntity<ErrorDto> handleOrbitNotFoundException(OrbitNotFoundException ex) {
        // UUID orbitNotFoundId = ex.getId();
        // String errorMessage = String.format("Orbit with ID '%s' not found.", orbitNotFoundId);
        // ErrorDto errorDto = new ErrorDto(errorMessage);
        // or
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
