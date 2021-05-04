package com.bgpark.photoshop.domain.order.domain;

import java.util.List;

public interface OrderRepository {

    Orders save(Orders order);

    List<Orders> findAll();
}
