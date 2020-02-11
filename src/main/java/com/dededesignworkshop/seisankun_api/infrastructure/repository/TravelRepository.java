package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.domain.object.Travel;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.TravelEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;


@Mapper
public interface TravelRepository {
    List<TravelEntity> findByUserId(@Param("user_id") Integer user_id);

    @Select("SELECT * FROM travels WHERE id = #{travel_id}")
    Optional<TravelEntity> findByTravelId(@Param("travel_id") Integer travel_id);

    @Insert("INSERT INTO travels (secret_word, name, travel_start, travel_end, private_flag, created_by, created_at, updated_by, updated_at)" +
            "VALUES (#{secretWord}, #{name}, #{travelStart}, #{travelEnd}, #{privateFlag}, #{createdBy}, #{createdAt}, #{updatedBy}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createTravel(Travel travel);

}