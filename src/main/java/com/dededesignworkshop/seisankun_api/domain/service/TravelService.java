package com.dededesignworkshop.seisankun_api.domain.service;

import com.dededesignworkshop.seisankun_api.domain.object.Travel;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.TravelEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.TravelRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TravelService {
    @NonNull
    private final TravelRepository travelRepository;

    public Stream<Travel> findByUserId(Integer user_id) {
        return this.travelRepository.findByUserId(user_id).stream().map(TravelEntity::toDomainTravelList);
    }

    public Optional<Travel> findByTravelId(Integer travel_id) {
        return this.travelRepository.findByTravelId(travel_id).map(TravelEntity::toDomainTravelList);
    }
}
