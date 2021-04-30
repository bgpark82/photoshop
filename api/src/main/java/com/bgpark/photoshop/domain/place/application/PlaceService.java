package com.bgpark.photoshop.domain.place.application;

import com.bgpark.photoshop.domain.place.domain.Place;
import com.bgpark.photoshop.domain.place.domain.PlaceRepository;
import com.bgpark.photoshop.domain.place.dto.PlaceRequest;
import com.bgpark.photoshop.domain.place.dto.PlaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceResponse save(PlaceRequest request) {
        final Place place = placeRepository.save(request.of());
        return PlaceResponse.of(place);
    }

    public List<PlaceResponse> findByKeyword(String keyword) {
        return placeRepository.findByNameStartsWith(keyword).stream()
                .map(PlaceResponse::of)
                .collect(toList());
    }
}
