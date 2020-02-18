package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.domain.object.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    @Insert("INSERT INTO users (uid, icon_image_path, name, gender, profile, created_by, created_at, updated_by, updated_at)" +
            "VALUES (#{uid}, #{iconImagePath}, #{name}, #{gender}, #{profile}, #{createdBy}, #{createdAt}, #{updatedBy}, #{updatedAt})")
    void createUserInfo(User user);
}
