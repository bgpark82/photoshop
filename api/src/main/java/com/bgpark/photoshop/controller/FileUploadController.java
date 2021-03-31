package com.bgpark.photoshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/upload")
public class FileUploadController {

    @PostMapping
    public ResponseEntity upload(@RequestParam MultipartFile file) {
        System.out.println(file);
        return ResponseEntity.created(URI.create("/")).build();
    }
}
