package com.cameraewebproject.exceptions;

import com.cameraewebproject.ui.model.response.ApiErrorMessage;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {CameraServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(CameraServiceException ex, WebRequest request) {
        ApiErrorMessage errorMessage = new ApiErrorMessage(new Date(),ex.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
