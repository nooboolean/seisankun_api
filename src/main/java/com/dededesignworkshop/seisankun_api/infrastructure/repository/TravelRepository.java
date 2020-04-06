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

    @Insert("INSERT INTO travels (hash_id, name, travel_start, travel_end, created_by, created_at, updated_by, updated_at)" +
            "VALUES (#{hashId}, #{name}, #{travelStart}, #{travelEnd}, #{createdBy}, #{createdAt}, #{updatedBy}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createTravel(Travel travel);

    void updateTravel(Travel travel);

    @Update("UPDATE travels SET updated_by = #{updatedBy}, updated_at = #{updatedAt}, deleted_by = #{updatedBy}, deleted_at = #{deletedAt}," +
            "delete_flag = 1 WHERE id = #{id}")
    void softDeleteTravel(Travel travel);

    @Delete("DELETE FROM travels WHERE id = #{travelId}")
    void deleteTravel(@Param("travelId") Integer travelId);

    @Select("SELECT id FROM travels WHERE hash_id = #{hash_id}")
    Integer getTravelId(String hash_id);

}
