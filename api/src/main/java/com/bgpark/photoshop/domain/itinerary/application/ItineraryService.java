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

        Itinerary itinerary = Itinerary.create(request.getName());

        List<Schedule> schedules = request.getSchedules().stream()
                .map(s -> Schedule.create(s))
                .collect(toList());

        itinerary.setSchedules(schedules);

        Itinerary itinerarySaved = itineraryRepository.save(itinerary);

        return ItineraryResponse.of(itinerarySaved);
    }
}
