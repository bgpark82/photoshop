package com.bgpark.photoshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @PostMapping("/orders")
    public ResponseEntity save() {
        return ResponseEntity.created(URI.create("/order/1")).build();
    }

}
