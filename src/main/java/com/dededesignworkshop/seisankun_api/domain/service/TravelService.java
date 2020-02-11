package com.dededesignworkshop.seisankun_api.domain.service;

import com.dededesignworkshop.seisankun_api.domain.object.Travel;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.TravelEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.TravelRepository;
import jdk.jfr.internal.Options;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public void createTravel(Travel travel){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        travel.setCreatedAt(now_date);
        travel.setUpdatedAt(now_date);
        this.travelRepository.createTravel(travel);
    }

    public Optional<Travel> updateTravel(Travel travel){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        travel.setUpdatedAt(now_date);
        this.travelRepository.updateTravel(travel);
        return this.travelRepository.findByTravelId(travel.getId()).map(TravelEntity::toDomainTravelList);
    }

    public void softDeleteTravel(Travel travel){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        travel.setUpdatedAt(now_date);
        travel.setDeletedAt(now_date);
        this.travelRepository.softDeleteTravel(travel);
    }

    public void deleteTravel(Integer travelId){
        this.travelRepository.deleteTravel(travelId);
    }
}
