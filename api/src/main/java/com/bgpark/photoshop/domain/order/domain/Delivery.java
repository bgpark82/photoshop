package com.bgpark.photoshop.domain.order.domain;

import com.bgpark.photoshop.domain.user.domain.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.bgpark.photoshop.domain.order.domain.DeliveryStatus.READY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    private Orders order;

    private Delivery(Address address, DeliveryStatus status) {
        this.address = address;
        this.status = status;
    }

    public static Delivery ready(Address address) {
        return new Delivery(address, READY);
    }

    public void addOrder(Orders order) {
        this.order = order;
    }
}
