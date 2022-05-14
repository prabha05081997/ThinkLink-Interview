package com.thinklink.cryptocurrencytracker.exception;

import com.thinklink.cryptocurrencytracker.model.response.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Message> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        return new ResponseEntity<>(new Message(exception.getMessage(), 404), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<Message> internalException(InternalException exception) {
        return new ResponseEntity<>(new Message(exception.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Message> badRequestException(BadRequestException exception) {
        return new ResponseEntity<>(new Message(exception.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Message> conflictException(ConflictException exception) {
        return new ResponseEntity<>(new Message(exception.getMessage(), 409), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Message> unauthorizedException(UnauthorizedException exception) {
        return new ResponseEntity<>(new Message(exception.getMessage(), 403), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AlreadyReportedException.class)
    public ResponseEntity<Message> alreadyReportedException(AlreadyReportedException exception) {
        return new ResponseEntity<>(new Message(exception.getMessage(), 208), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Message> customValidationException(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(new Message(exception.getBindingResult().getFieldError().getDefaultMessage(), 400),
                HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Message> exceptionHandlerHandler(Exception exception) {
//        return new ResponseEntity<>(new Message(exception.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
