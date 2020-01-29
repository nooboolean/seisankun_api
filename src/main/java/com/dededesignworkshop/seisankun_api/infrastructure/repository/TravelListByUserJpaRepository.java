package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelListByUserJpaRepository extends JpaRepository<UserEntity, Integer> {
}
