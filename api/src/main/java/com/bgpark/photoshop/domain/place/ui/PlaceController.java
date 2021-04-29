package com.bgpark.photoshop.domain.place.ui;

import com.bgpark.photoshop.domain.place.application.PlaceService;
import com.bgpark.photoshop.domain.place.domain.Place;
import com.bgpark.photoshop.domain.place.domain.PlaceRepository;
import com.bgpark.photoshop.domain.place.dto.PlaceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity save(@RequestBody PlaceRequest request) {
        Place place = placeService.save(request);
        return ResponseEntity.created(URI.create("/place/"+ place.getId())).build();
    }
}
