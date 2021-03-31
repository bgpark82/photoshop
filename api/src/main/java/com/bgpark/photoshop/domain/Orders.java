package com.bgpark.photoshop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // mappedBy : orderItem의 Order 변수명
    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Delivery delivery;

    private void addOrderItems(List<OrderItem> orderItems) {
        orderItems.forEach(this::addOrderItem);
    }

    private void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.addOrder(this);
    }

    private void startDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.addOrder(this);
    }

    private void addUser(User user) {
        this.user = user;
    }

    public static Orders create(User user, Delivery delivery, List<OrderItem> orderItems) {
        // 기본 생성자를 이용하여 private set 메소드를 만드는 것이 생성자를 더럽히는(?) 것보다 나을듯하다
        Orders order = new Orders();
        order.addOrderItems(orderItems);
        order.startDelivery(delivery);
        order.addUser(user);
        return order;
    }
}
