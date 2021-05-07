package com.bgpark.photoshop.domain.order.domain;

import com.bgpark.photoshop.domain.item.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private Orders order;

    @CreatedDate
    private LocalDateTime orderedDate;

    private int orderPrice;

    private int count;

    public static OrderItem create(Item item, int count) {
        item.decreaseStock(count);

        OrderItem orderItem = new OrderItem();
        orderItem.item = item;
        orderItem.orderPrice = item.getPrice();
        orderItem.count = count;
        return orderItem;
    }

    public void addOrder(Orders order) {
        this.order = order;
    }
}
