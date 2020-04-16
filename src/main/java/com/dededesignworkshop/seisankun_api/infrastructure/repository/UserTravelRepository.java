package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.domain.object.UserTravel;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserTravelRepository {

    @Insert("INSERT INTO user_travel (travel_id, user_id, created_by, created_at, updated_by, updated_at)" +
            "VALUES (#{travelId}, #{userId}, #{createdBy}, #{createdAt}, #{updatedBy}, #{updatedAt})")
    void createUserTravel(UserTravel userTravel);

    @Delete("DELETE FROM user_travel WHERE user_id = #{userId} AND travel_id = #{travelId}")
    void deleteUserTravel(UserTravel userTravel);

    @Select("SELECT * from user_travel WHERE user_id = #{userId} AND travel_id = #{travelId} AND (delete_flag = 0 OR delete_flag IS NULL)")
    UserTravel findByUserIdAndTravelId(Integer userId, Integer travelId);

}
