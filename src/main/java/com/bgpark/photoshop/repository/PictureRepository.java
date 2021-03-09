package com.bgpark.photoshop.repository;

import com.bgpark.photoshop.domain.item.Picture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
public class PictureRepository {

    private final EntityManager em;

    @Transactional
    public Picture save(Picture picture){
        em.persist(picture);
        return picture;
    }
}
