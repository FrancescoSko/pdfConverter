package com.rep0.pdfToText.service;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.rep0.pdfToText.exception.PdfProcessingException;
import com.rep0.pdfToText.exception.FileConversionException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfService {
    /**
     * Extracts text content from a single PDF file.
     *
     * @param multipartFile the PDF file uploaded as MultipartFile
     * @return the extracted text content of the PDF
     */
    public String extractTextFromPDF(MultipartFile multipartFile) {

        File tempFile = null;

        try {
            tempFile = convertMultipartFileToFile(multipartFile);

            try (PDDocument document = PDDocument.load(tempFile)) {
                PDFTextStripper pdfTextStripper = new PDFTextStripper();
                return pdfTextStripper.getText(document);
            } catch (IOException e) {
                throw new PdfProcessingException("Error processing PDF file", e);
            }
        } finally {
            if (tempFile != null) {
                tempFile.delete();
            }
        }
    }


    /**
     * Extracts text content from multiple PDF files.
     *
     * @param files array of PDF files uploaded as MultipartFile
     * @return a list of extracted text content for each PDF
     */
    public List<String> extractTextFromMultiplePDFs(MultipartFile[] files) {
        List<String> extractedTexts = new ArrayList<>();

        for (MultipartFile multipartFile : files) {
            extractedTexts.add(extractTextFromPDF(multipartFile));
        }
        return extractedTexts;
    }

    /**
     * Converts a MultipartFile to a temporary File object.
     *
     * @param file the MultipartFile to convert
     * @return a temporary File object representing the uploaded file
     */
    private File convertMultipartFileToFile(MultipartFile file) {
        try {
            // Create a temporary file with the same name as the uploaded file
            String prefix = file.getOriginalFilename() != null ? file.getOriginalFilename().replaceAll("\\s+", "_") : "temp";
            File tempFile = Files.createTempFile(prefix, ".pdf").toFile();

            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(file.getBytes());
            }
            return tempFile;
        } catch (IOException e) {
            throw new FileConversionException("Error converting MultipartFile to File", e);
        }
    }
}
