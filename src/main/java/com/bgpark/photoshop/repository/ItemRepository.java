package com.bgpark.photoshop.repository;

import com.bgpark.photoshop.domain.item.Item;
import com.bgpark.photoshop.domain.item.Picture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Picture save(Item item){
        em.persist(item);
        return (Picture) item;
    }

    public Optional<Picture> findById(Long id){
        Picture picture = em.find(Picture.class, id);
        return Optional.of(picture);
    }
}
