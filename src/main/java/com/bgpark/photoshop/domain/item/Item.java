package com.bgpark.photoshop.domain.item;

import com.bgpark.photoshop.domain.BaseEntity;
import com.bgpark.photoshop.domain.Orders;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "DTYPE")
@ToString(callSuper = true)
public abstract class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    @ManyToOne
    private Orders order;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void addOrder(Orders order) {
        this.order = order;
    }
}
