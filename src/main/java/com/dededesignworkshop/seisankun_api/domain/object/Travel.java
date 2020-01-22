package com.dededesignworkshop.seisankun_api.domain.object;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Travel {
    private Integer id;

    private String name;

    private String travelStart;

    private String travelEnd;

    private Integer privateFlag;

    private String createdAt;

}
