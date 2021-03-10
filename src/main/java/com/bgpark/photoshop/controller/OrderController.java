package com.bgpark.photoshop.controller;

import com.bgpark.photoshop.domain.Orders;
import com.bgpark.photoshop.domain.User;
import com.bgpark.photoshop.domain.item.Item;
import com.bgpark.photoshop.dto.OrderDto;
import com.bgpark.photoshop.repository.OrderRepository;
import com.bgpark.photoshop.repository.ItemRepository;
import com.bgpark.photoshop.repository.UserRepository;
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

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @PostMapping("/orders")
    @Transactional
    public ResponseEntity save(@RequestBody OrderDto.Req request) {

        User user = userRepository.findById(request.getUserId()).get();
        Item picture = itemRepository.findById(request.getItemId()).get();

        Orders order = Orders.builder()
                .user(user)
                .item(picture)
                .build();

        orderRepository.save(order);

        OrderDto.Res response = OrderDto.Res.of(order);

        return ResponseEntity
                .created(URI.create(String.format("/order/%d", response.getId())))
                .body(response);
    }

}
