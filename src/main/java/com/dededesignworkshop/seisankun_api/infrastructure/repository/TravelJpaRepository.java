package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.infrastructure.entity.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelJpaRepository extends JpaRepository<TravelEntity, Integer> {
}
