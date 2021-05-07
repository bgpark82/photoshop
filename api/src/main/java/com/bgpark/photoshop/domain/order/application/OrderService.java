package com.bgpark.photoshop.domain.order.application;

import com.bgpark.photoshop.domain.auth.domain.UserDetails;
import com.bgpark.photoshop.domain.item.domain.Item;
import com.bgpark.photoshop.domain.item.domain.ItemRepository;
import com.bgpark.photoshop.domain.order.domain.Delivery;
import com.bgpark.photoshop.domain.order.domain.OrderItem;
import com.bgpark.photoshop.domain.order.domain.OrderRepository;
import com.bgpark.photoshop.domain.order.domain.Orders;
import com.bgpark.photoshop.domain.order.dto.OrderItemRequest;
import com.bgpark.photoshop.domain.order.dto.OrderRequest;
import com.bgpark.photoshop.domain.order.dto.OrderResponse;
import com.bgpark.photoshop.domain.user.domain.User;
import com.bgpark.photoshop.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public OrderResponse save(UserDetails userDetails, OrderRequest request) {
        User user = findUserById(userDetails.getId());
        Delivery delivery = Delivery.ready(user.getHomeAddress());
        List<OrderItem> orderItems = getOrderItems(request);
        Orders order = Orders.start(user, delivery, orderItems);

        orderRepository.save(order);

        return OrderResponse.create(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> findAll(UserDetails userDetails) {
        return orderRepository.findAll()
                .stream()
                .map(OrderResponse::create)
                .collect(Collectors.toList());
    }

    private List<OrderItem> getOrderItems(OrderRequest orderRequest) {
        return orderRequest.getOrderItems().stream()
                .map(orderItemRequest -> OrderItem.create(
                        getItem(orderItemRequest),
                        orderItemRequest.getCount()))
                .collect(Collectors.toList());
    }

    private Item getItem(OrderItemRequest orderItemRequest) {
        return itemRepository.findById(orderItemRequest.getItemId()).orElseThrow(() -> new IllegalArgumentException("아이템이 존재하지 않습니다"));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }


}
