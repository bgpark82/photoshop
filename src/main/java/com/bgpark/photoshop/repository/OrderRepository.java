package com.bgpark.photoshop.repository;

import com.bgpark.photoshop.domain.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public Orders save(Orders order) {
        em.persist(order);
        return order;
    }
}
