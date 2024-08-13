package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.domain.exception.CannotCancelStateException;
import kr.co.ordermanagement.domain.exception.EntityNotFoundException;
import kr.co.ordermanagement.domain.exception.NotEnoughAmountException;
import kr.co.ordermanagement.presentation.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotEnoughAmountException.class)
    public ResponseEntity<ErrorMessageDto> handleNotEnoughAmountException(NotEnoughAmountException notEnoughAmountException) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(notEnoughAmountException.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(entityNotFoundException.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CannotCancelStateException.class)
    public ResponseEntity<ErrorMessageDto> handleCannotCancelStateException(CannotCancelStateException cannotCancelStateException) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(cannotCancelStateException.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
