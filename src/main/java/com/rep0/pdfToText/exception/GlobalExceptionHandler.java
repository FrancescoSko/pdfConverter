package com.rep0.pdfToText.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({PdfProcessingException.class})
    public ResponseEntity<String> handlePdfProcessingException(PdfProcessingException ex) {
        return new ResponseEntity<>("Errore durante l'elaborazione del file PDF: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({FileConversionException.class})
    public ResponseEntity<String> handleFileConversionException(FileConversionException ex) {
        return new ResponseEntity<>("Errore durante la conversione del file: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Si è verificato un errore inaspettato: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}