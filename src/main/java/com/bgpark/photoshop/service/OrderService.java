package com.bgpark.photoshop.service;

import com.bgpark.photoshop.domain.Delivery;
import com.bgpark.photoshop.domain.OrderItem;
import com.bgpark.photoshop.domain.Orders;
import com.bgpark.photoshop.domain.User;
import com.bgpark.photoshop.domain.item.Item;
import com.bgpark.photoshop.dto.OrderDto;
import com.bgpark.photoshop.repository.ItemRepository;
import com.bgpark.photoshop.repository.OrderRepository;
import com.bgpark.photoshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public OrderDto.Res save(OrderDto.Req request) {
        User user = findUserById(request);
        Item item = findItemById(request);

        OrderItem orderItem = OrderItem.create(item, request.getCount());
        Delivery delivery = Delivery.ready(user.getHomeAddress());

        Orders order = Orders.create(user, delivery, orderItem);

        orderRepository.save(order);

        return OrderDto.Res.of(order);
    }

    private Item findItemById(OrderDto.Req request) {
        return itemRepository.findById(request.getItemId()).get();
    }

    private User findUserById(OrderDto.Req request) {
        return userRepository.findById(request.getUserId()).get();
    }
}
