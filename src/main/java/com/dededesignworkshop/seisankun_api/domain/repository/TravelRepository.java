package com.dededesignworkshop.seisankun_api.domain.repository;

import com.dededesignworkshop.seisankun_api.domain.object.Travel;

import java.util.Optional;

public interface TravelRepository {
    Optional<Travel> findById(Integer id);
}
