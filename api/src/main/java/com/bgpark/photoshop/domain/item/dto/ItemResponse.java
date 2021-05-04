package com.bgpark.photoshop.domain.item.dto;

import com.bgpark.photoshop.domain.item.domain.Item;
import lombok.Getter;

@Getter
public class ItemResponse {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    public static ItemResponse create(Item item) {
        ItemResponse response = new ItemResponse();
        response.id = item.getId();
        response.name = item.getName();
        response.price = item.getPrice();
        response.stockQuantity = item.getStockQuantity();
        return response;
    }
}
