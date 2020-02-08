package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.infrastructure.entity.TravelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;


@Mapper
public interface TravelRepository {
    List<TravelEntity> findByUserId(@Param("user_id") Integer user_id);

    @Select("SELECT * FROM travels WHERE id = #{travel_id}")
    Optional<TravelEntity> findByTravelId(@Param("travel_id") Integer travel_id);
}
