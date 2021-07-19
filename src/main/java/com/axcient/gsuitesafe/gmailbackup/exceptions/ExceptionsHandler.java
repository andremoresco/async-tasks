package com.axcient.gsuitesafe.gmailbackup.exceptions;

import com.axcient.gsuitesafe.gmailbackup.http.HttpErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BackupNotFoundException.class)
    public ResponseEntity<HttpErrorResponse> handleBackupNotFoundException(BackupNotFoundException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new HttpErrorResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(BackupNotReadyToExportException.class)
    public ResponseEntity<HttpErrorResponse> handleBackupNotReadyToExportException(BackupNotReadyToExportException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new HttpErrorResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpErrorResponse> handleDefaultException(Exception e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new HttpErrorResponse("INTERNAL_SERVER_ERROR", e.getMessage()));
    }
}
