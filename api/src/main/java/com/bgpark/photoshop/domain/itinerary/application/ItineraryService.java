package com.bgpark.photoshop.domain.itinerary.application;

import com.bgpark.photoshop.domain.itinerary.dto.ItineraryRequest;
import com.bgpark.photoshop.domain.itinerary.dto.ItineraryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItineraryService {

    public ItineraryResponse save(ItineraryRequest request) {

        return new ItineraryResponse();
    }
}
