package com.bgpark.photoshop.domain.order.ui;

import com.bgpark.photoshop.domain.order.application.OrderService;
import com.bgpark.photoshop.domain.order.dto.OrderRequest;
import com.bgpark.photoshop.domain.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity save(@RequestBody OrderRequest request) {
        OrderResponse response = orderService.save(request);

        return ResponseEntity
                .created(URI.create(String.format("/order/%d", response.getId())))
                .body(response);
    }

    @GetMapping("/orders")
    public ResponseEntity getAll() {
        List<OrderResponse> orders = orderService.findAll();

        return ResponseEntity.ok().body(orders);
    }
}
