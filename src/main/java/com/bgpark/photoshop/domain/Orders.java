package com.bgpark.photoshop.domain;

import com.bgpark.photoshop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Lazy;

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

    @ManyToOne
    private User user;

    @OneToMany
    private List<Item> items = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Delivery delivery;

    private Orders(User user, Delivery delivery, Item... items) {
        addItems(items);
        // this.delivery = delivery;
        startDelivery(delivery);
        this.user = user;
    }

    private void addItems(Item... items) {
        Arrays.stream(items)
                .forEach(item -> addItem(item));
    }

    public void startDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.addOrder(this);
    }

    public void addItem(Item item) {
        items.add(item);
        item.addOrder(this);
    }

    public static Orders create(User user, Delivery delivery, Item... items) {
        return new Orders(user, delivery, items);
    }


}
