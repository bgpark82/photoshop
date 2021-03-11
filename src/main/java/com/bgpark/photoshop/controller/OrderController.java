package com.bgpark.photoshop.controller;

import com.bgpark.photoshop.dto.OrderDto;
import com.bgpark.photoshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/orders")
    @Transactional
    public ResponseEntity save(@RequestBody OrderDto.Req request) {

        OrderDto.Res response = orderService.save(request);

        return ResponseEntity
                .created(URI.create(String.format("/order/%d", response.getId())))
                .body(response);
    }

}
