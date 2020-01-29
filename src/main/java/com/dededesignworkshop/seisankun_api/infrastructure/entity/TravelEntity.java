package com.dededesignworkshop.seisankun_api.infrastructure.entity;

import com.dededesignworkshop.seisankun_api.domain.object.Travel;
import com.dededesignworkshop.seisankun_api.domain.object.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "travels")
public class TravelEntity {

    @ManyToMany(mappedBy = "travelListByUser")
    private List<User> Users;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "secret_word")
    private String secretWord;

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

    @Column(name = "delete_flag")
    private Integer deleteFlag;

//    public static TravelEntity build(Travel travel) {
//        return TravelEntity.builder()
//                .id(travel.getId())
//                .name(travel.getName())
//                .travelStart(travel.getTravelStart())
//                .build();
//    }

    public Travel toDomainTravel() {
        return Travel.builder()
                .id(this.id)
                .secretWord(this.secretWord)
                .name(this.name)
                .travelStart(this.travelStart)
                .travelEnd(this.travelEnd)
                .privateFlag(this.privateFlag)
                .createdBy(this.createdBy)
                .createdAt(this.createdAt)
                .updatedBy(this.updatedBy)
                .updatedAt(this.updatedAt)
                .build();
    }
}
