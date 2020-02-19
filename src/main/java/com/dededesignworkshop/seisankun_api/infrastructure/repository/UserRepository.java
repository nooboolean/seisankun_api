package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.domain.object.User;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserRepository {
    @Insert("INSERT INTO users (uid, icon_image_path, name, gender, profile, created_by, created_at, updated_by, updated_at)" +
            "VALUES (#{uid}, #{iconImagePath}, #{name}, #{gender}, #{profile}, #{createdBy}, #{createdAt}, #{updatedBy}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createUserInfo(User user);

    @Select("SELECT * FROM users WHERE uid = #{uid}")
    Optional<UserEntity> findByUid(String uid);
}
