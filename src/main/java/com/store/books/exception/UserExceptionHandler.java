package com.store.books.exception;

import com.store.books.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

// Allows us to handle multiple exception at one place.
@ControllerAdvice
// Allows us to print message over console.
@Slf4j
public class UserExceptionHandler {

    // Declaring a constant message.
    private static final String MSG = "Exception while processing rest request!";

    // Handling exception for invalid method arguments.
    // Used to handle exception and to send custom response to client.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgNotValidException(MethodArgumentNotValidException exception) {
        // Getting all errors in a list of ObjectError.
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();

        // Converting list of ObjectError to list of String.
        // Using Stream API.
        List<String> errMsg = allErrors.stream()
                .map(objErr -> objErr.getDefaultMessage()).collect(Collectors.toList());

        ResponseDTO responseDTO = new ResponseDTO(MSG, errMsg, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    // Handling custom exception.
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ResponseDTO> handleUserException(UserException exception) {
        ResponseDTO responseDTO = new ResponseDTO(MSG, exception.getMessage(), null);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    // Handling exception for incorrect date format.
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO> handleHttpMsgNotReadableException(HttpMessageNotReadableException exception) {
        log.error("Invalid Date Format!", exception);

        ResponseDTO responseDto = new ResponseDTO(MSG, "Should have date in the format 'yyyy-mm-dd'!", null);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
