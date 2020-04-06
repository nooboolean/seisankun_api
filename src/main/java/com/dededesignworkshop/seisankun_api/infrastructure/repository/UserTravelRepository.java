package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.domain.object.UserTravel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserTravelRepository {

    @Insert("INSERT INTO user_travel (travel_id, user_id, created_by, created_at, updated_by, updated_at)" +
            "VALUES (#{travelId}, #{userId}, #{createdBy}, #{createdAt}, #{updatedBy}, #{updatedAt})")
    void createUserTravel(UserTravel userTravel);

    @Update("UPDATE user_travel SET updated_by = #{updatedBy}, updated_at = #{updatedAt}, deleted_by = #{deletedBy}, deleted_at = #{deletedAt}," +
            "delete_flag = 1 WHERE travel_id = #{travelId} AND user_id = #{userId} AND (delete_flag = 0 OR delete_flag IS NULL)")
    void deleteUserTravel(UserTravel userTravel);

    @Select("SELECT * from user_travel WHERE user_id = #{userId} AND travel_id = #{travelId} AND (delete_flag = 0 OR delete_flag IS NULL)")
    UserTravel findByUserIdAndTravelId(Integer userId, Integer travelId);

}
