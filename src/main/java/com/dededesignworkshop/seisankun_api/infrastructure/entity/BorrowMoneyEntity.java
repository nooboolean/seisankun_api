package com.dededesignworkshop.seisankun_api.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowMoneyEntity {

    private Integer id;

    private Integer travelId;

    private Integer borrowerId;

    private Integer lenderId;

    private Integer money;

    private Integer createdBy;

    private String createdAt;

    private Integer updatedBy;

    private String updatedAt;

    private Integer deletedBy;

    private String deletedAt;

    private Integer deleteFlag;
}
