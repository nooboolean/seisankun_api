package com.dededesignworkshop.seisankun_api.domain.object;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Travel {

    private Integer id;

    private String name;

    private String secretWord;

    private String travelStart;

    private String travelEnd;

    private Integer privateFlag;

    private Integer createdBy;

    private String createdAt;

    private Integer updatedBy;

    private String updatedAt;

    private Integer deletedBy;

    private String deletedAt;

    private Integer deleteFlag;

}
