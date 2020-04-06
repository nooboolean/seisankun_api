package com.dededesignworkshop.seisankun_api.domain.object;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Payment {

    private Integer id;

    private Integer travelId;

    private Integer payerId;

    private String title;

    private Integer amount;

    private List<Integer> borrowers;

    private Integer createdBy;

    private String createdAt;

    private Integer updatedBy;

    private String updatedAt;

    private Integer deletedBy;

    private String deletedAt;

    private Integer deleteFlag;
}
