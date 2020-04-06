package com.dededesignworkshop.seisankun_api.infrastructure.entity;

import com.dededesignworkshop.seisankun_api.domain.object.Travel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelEntity {

    private Integer id;

    private String hashId;

    private String name;

    private String travelStart;

    private String travelEnd;

    private Integer createdBy;

    private String createdAt;

    private Integer updatedBy;

    private String updatedAt;

    private Integer deletedBy;

    private String deletedAt;

    private Integer deleteFlag;

    public Travel toDomainTravelList() {
        return Travel.builder()
                .id(this.id)
                .hashId(this.hashId)
                .name(this.name)
                .travelStart(this.travelStart)
                .travelEnd(this.travelEnd)
                .createdBy(this.createdBy)
                .createdAt(this.createdAt)
                .updatedBy(this.updatedBy)
                .updatedAt(this.updatedAt)
                .build();
    }
}
