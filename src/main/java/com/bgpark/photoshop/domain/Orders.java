package com.bgpark.photoshop.domain;

import com.bgpark.photoshop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    @Builder
    public Orders(User user, Item item) {
        this.user = user;
        this.items.add(item);
    }
}
