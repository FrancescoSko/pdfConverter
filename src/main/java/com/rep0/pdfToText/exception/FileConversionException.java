package com.rep0.pdfToText.exception;


public class FileConversionException extends RuntimeException {
    public FileConversionException(String message) {
        super(message);
    }

    public FileConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}