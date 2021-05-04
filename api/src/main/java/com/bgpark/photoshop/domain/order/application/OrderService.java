package com.bgpark.photoshop.domain.order.application;

import com.bgpark.photoshop.domain.item.domain.Item;
import com.bgpark.photoshop.domain.item.domain.ItemRepository;
import com.bgpark.photoshop.domain.order.domain.Delivery;
import com.bgpark.photoshop.domain.order.domain.OrderItem;
import com.bgpark.photoshop.domain.order.domain.OrderRepository;
import com.bgpark.photoshop.domain.order.domain.Orders;
import com.bgpark.photoshop.domain.order.dto.OrderDto;
import com.bgpark.photoshop.domain.user.domain.User;
import com.bgpark.photoshop.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.bgpark.photoshop.domain.order.dto.OrderItemDto.Req;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public OrderDto.Res save(OrderDto.Req request) {
        User user = findUserById(request);
        List<OrderItem> orderItems = request.getOrderItems().stream()
                .map(this::createOrderItem)
                .collect(Collectors.toList());

        Delivery delivery = Delivery.ready(user.getHomeAddress());
        Orders order = Orders.create(user, delivery, orderItems);

        orderRepository.save(order);

        return OrderDto.Res.of(order);
    }

    @Transactional(readOnly = true)
    private OrderItem createOrderItem(Req orderItem) {
        return OrderItem.create(
                findItemById(orderItem.getItemId()),
                orderItem.getCount());
    }

    @Transactional(readOnly = true)
    public List<OrderDto.Res> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(OrderDto.Res::of)
                .collect(Collectors.toList());
    }

    private Item findItemById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("아이템이 존재하지 않습니다"));
    }

    private User findUserById(OrderDto.Req request) {
        return userRepository.findById(request.getUserId()).get();
    }


}
