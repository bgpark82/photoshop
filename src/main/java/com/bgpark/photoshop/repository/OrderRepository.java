package com.bgpark.photoshop.repository;

import com.bgpark.photoshop.domain.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public Orders save(Orders order) {
        em.persist(order);
        return order;
    }

    public List<Orders> findAll() {
        return em.createQuery("SELECT o FROM Orders o" +
                        " join fetch o.user u" +
                        " join fetch o.delivery d"
//                        " join fetch o.orderItems oi" +
//                        " join fetch oi.item i"
                , Orders.class)
                .setFirstResult(1)
                .setMaxResults(100)
                .getResultList();
    }
}
