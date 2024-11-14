package com.rep0.pdfToText.model;

import org.springframework.http.HttpStatus;

public class Response {

    private HttpStatus status;
    private String message;
    private String extractedText;
}
