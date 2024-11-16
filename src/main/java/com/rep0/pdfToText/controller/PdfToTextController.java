package com.rep0.pdfToText.controller;

import com.rep0.pdfToText.model.Response;
import com.rep0.pdfToText.service.PdfToTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
public class PdfToTextController implements PdfToTextApi{


    @Override
    public ResponseEntity<Response> uploadSinglePDF(MultipartFile file) {

    }

    @Override
    public ResponseEntity<List<Response>> uploadMultiplePDFs(MultipartFile[] files) {
    }
}
