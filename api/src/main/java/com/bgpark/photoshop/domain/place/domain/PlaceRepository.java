package com.bgpark.photoshop.domain.place.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findByNameStartsWith(String keyword);
}
