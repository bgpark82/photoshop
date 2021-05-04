package com.bgpark.photoshop.domain.item.domain;

import com.bgpark.photoshop.domain.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    protected Item() { }

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void ready(int count){
        final int restStock = stockQuantity - count;
        if(restStock < 0) {
            throw new IllegalArgumentException("수량이 부족합니다");
        }
        this.stockQuantity = restStock;
    }
}
