package com.bgpark.photoshop.domain.itinerary.ui;

import com.bgpark.photoshop.domain.itinerary.application.ItineraryService;
import com.bgpark.photoshop.domain.itinerary.dto.ItineraryRequest;
import com.bgpark.photoshop.domain.itinerary.dto.ItineraryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/itineraries")
public class ItineraryController {

    private final ItineraryService itineraryService;

    @PostMapping
    public ResponseEntity save(@RequestBody ItineraryRequest request) {
        ItineraryResponse itinerary = itineraryService.save(request);
        return ResponseEntity
                .created(URI.create("/itinerary/1"))
                .body(itinerary);
    }
}
