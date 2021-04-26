package com.bgpark.photoshop.domain.item.domain;

import com.bgpark.photoshop.domain.item.domain.Item;
import com.bgpark.photoshop.domain.item.domain.Picture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Item save(Item item){
        em.persist(item);
        return item;
    }

    public Optional<Item> findById(Long id){
        Item item = em.find(Picture.class, id);
        return Optional.of(item);
    }
}
