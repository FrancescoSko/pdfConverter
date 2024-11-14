package com.rep0.pdfToText.controller;

import com.rep0.pdfToText.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("${service.base.url}+{service.api.version}")
public interface PdfToTextApi {

    static String prefix = "/extract-to-text";

    @PostMapping(
            value = {prefix + "/upload-single"},
            consumes = {"multipart/form-data"}
    )
    public ResponseEntity<Response> uploadSinglePDF(@RequestParam("file") MultipartFile file);

    @PostMapping(
            value = {prefix + "/upload-multiple"},
            consumes = {"multipart/form-data"}
    )
    public ResponseEntity<List<Response>> uploadMultiplePDFs(@RequestPart("files") MultipartFile[] files);



}