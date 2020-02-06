package com.dededesignworkshop.seisankun_api.infrastructure.entity;

import com.dededesignworkshop.seisankun_api.domain.object.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private Integer uid;

    private String iconImagePath;

    private String name;

    private Integer gender;

    private String profile;

    private Integer createdBy;

    private String createdAt;

    private Integer updatedBy;

    private String updatedAt;

    private Integer deletedBy;

    private String deletedAt;

    private Integer deleteFlag;

    public User toDomainUser() {
        return User.builder()
                .uid(this.uid)
                .iconImagePath(this.iconImagePath)
                .name(this.name)
                .gender(this.gender)
                .profile(this.profile)
                .createdBy(this.createdBy)
                .createdAt(this.createdAt)
                .updatedBy(this.updatedBy)
                .updatedAt(this.updatedAt)
                .build();
    }

}
