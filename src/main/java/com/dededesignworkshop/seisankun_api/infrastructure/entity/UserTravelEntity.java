package com.dededesignworkshop.seisankun_api.infrastructure.entity;

import com.dededesignworkshop.seisankun_api.domain.object.User;
import com.dededesignworkshop.seisankun_api.domain.object.UserTravel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTravelEntity {
    private Integer id;

    private Integer travelId;

    private Integer userId;

    private Integer createdBy;

    private String createdAt;

    private Integer updatedBy;

    private String updatedAt;

    private Integer deletedBy;

    private String deletedAt;

    private Integer deleteFlag;

    public UserTravel toDomainUserTravel() {
        return UserTravel.builder()
                .id(this.id)
                .travelId(this.travelId)
                .userId(this.userId)
                .createdBy(this.createdBy)
                .createdAt(this.createdAt)
                .updatedBy(this.updatedBy)
                .updatedAt(this.updatedAt)
                .build();
    }
}
