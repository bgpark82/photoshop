package com.bgpark.photoshop.domain.itinerary.application;

import com.bgpark.photoshop.domain.itinerary.domain.Itinerary;
import com.bgpark.photoshop.domain.itinerary.domain.ItineraryRepository;
import com.bgpark.photoshop.domain.itinerary.domain.Schedule;
import com.bgpark.photoshop.domain.itinerary.dto.ItineraryRequest;
import com.bgpark.photoshop.domain.itinerary.dto.ItineraryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;

    public ItineraryResponse save(ItineraryRequest request) {
        final Itinerary itinerary = itineraryRepository.save(getItinerary(request));
        return ItineraryResponse.of(itinerary);
    }

    private Itinerary getItinerary(ItineraryRequest request) {
        final Itinerary itinerary = Itinerary.create(request.getName());
        final List<Schedule> schedules = getSchedules(request);
        itinerary.setSchedules(schedules);
        return itinerary;
    }

    private List<Schedule> getSchedules(ItineraryRequest request) {
        return request.getSchedules().stream()
                .map(Schedule::create)
                .collect(toList());
    }
}
