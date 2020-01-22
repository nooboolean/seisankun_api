package com.dededesignworkshop.seisankun_api.infrastructure.entity;

import com.dededesignworkshop.seisankun_api.domain.object.Travel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "travels")
public class TravelEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "travel_start")
    private String travelStart;

    @Column(name = "travel_end")
    private String travelEnd;

    @Column(name = "private_flag")
    private Integer privateFlag;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "deleted_by")
    private Integer deletedBy;

    @Column(name = "deleted_at")
    private String deletedAt;

    @Column(name = "deleted_flag")
    private Integer deletedFlag;

    public static TravelEntity build(Travel travel) {
        return TravelEntity.builder()
                .id(travel.getId())
                .name(travel.getName())
                .travelStart(travel.getTravelStart())
                .build();
    }

    public Travel toDomainTravel() {
        return Travel.builder()
                .id(this.id)
                .name(this.name)
                .travelStart(this.travelStart)
                .travelEnd(this.travelEnd)
                .privateFlag(this.privateFlag)
                .createdAt(this.createdAt)
                .build();
    }
}
