package com.dededesignworkshop.seisankun_api.domain.object;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserTravel {

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
}
