package com.dededesignworkshop.seisankun_api.domain.object;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {

    private Integer id;

    private Integer travelId;

    private Integer payerId;

    private Integer amount;

    private Integer createdBy;

    private String createdAt;

    private Integer updatedBy;

    private String updatedAt;

    private Integer deletedBy;

    private String deletedAt;

    private Integer deleteFlag;
}
