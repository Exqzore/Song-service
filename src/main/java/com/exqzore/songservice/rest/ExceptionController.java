package com.exqzore.songservice.rest;

import com.exqzore.songservice.exception.InternalServerException;
import com.exqzore.songservice.exception.InvalidBodyOrValidationException;
import com.exqzore.songservice.exception.ResourceNotFoundException;
import com.exqzore.songservice.model.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerException.class)
    public ErrorMessageDto handleNotFound(InternalServerException exception) {
        return new ErrorMessageDto(500, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidBodyOrValidationException.class)
    public ErrorMessageDto handleValidation(InvalidBodyOrValidationException exception) {
        return new ErrorMessageDto(400, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorMessageDto handleValidation(ResourceNotFoundException exception) {
        return new ErrorMessageDto(404, exception.getMessage());
    }
}
