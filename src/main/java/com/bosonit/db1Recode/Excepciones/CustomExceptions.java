package com.bosonit.db1Recode.Excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ControllerAdvice
public class CustomExceptions {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomError entityNotFoundException(EntityNotFoundException entityNotFoundException) {
        return new CustomError(entityNotFoundException.getMessage(),404);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public CustomError unprocessableEntityException(UnprocessableEntityException unprocessableEntityException) {
        return new CustomError(unprocessableEntityException.getMessage(),422);
    }
}
