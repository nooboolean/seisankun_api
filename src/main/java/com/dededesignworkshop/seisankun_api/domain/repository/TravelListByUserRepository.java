package com.dededesignworkshop.seisankun_api.domain.repository;

import com.dededesignworkshop.seisankun_api.domain.object.Travel;

import java.util.List;

public interface TravelListByUserRepository {
    List<Travel> findByUserId(Integer user_id);
}
