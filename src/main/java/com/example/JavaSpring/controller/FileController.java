package com.example.JavaSpring.controller;

import com.example.JavaSpring.service.YandexObjectStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files")
public class FileController {

    private final YandexObjectStorageService storageService;

    public FileController(YandexObjectStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/upload")
    public ResponseEntity<String> getUploadUrl(@RequestParam String fileName) {
        String url = storageService.generateUploadUrl(fileName);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/download")
    public ResponseEntity<String> getDownloadUrl(@RequestParam String fileName) {
        String url = storageService.generateDownloadUrl(fileName);
        return ResponseEntity.ok(url);
    }
}
