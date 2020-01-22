package com.dededesignworkshop.seisankun_api.infrastructure.entity;

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
@Table(name = "user_travel")
public class UserTravelEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "travel_id")
    private Integer travelId;

    @Column(name = "user_id")
    private Integer userId;

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

}
