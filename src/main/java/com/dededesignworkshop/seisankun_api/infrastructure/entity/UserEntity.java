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
@Table(name = "users")
public class UserEntity {
    @ManyToMany
    @JoinTable
               (
                    name = "user_travel",
                    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "uid"),
                    inverseJoinColumns = @JoinColumn(name = "travel_id", referencedColumnName = "id")
                )
    private List<Travel> travelListByUser;

    @Id
    @Column(name = "uid")
    private Integer uid;

    @Column(name = "icon_image_path")
    private String iconImagePath;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "profile")
    private String profile;

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

    public User toDomainUser() {
        return User.builder()
                .uid(this.uid)
                .iconImagePath(this.iconImagePath)
                .name(this.name)
                .gender(this.gender)
                .profile(this.profile)
                .createdBy(this.createdBy)
                .createdAt(this.createdAt)
                .updatedBy(this.updatedBy)
                .updatedAt(this.updatedAt)
                .build();
    }

//    public Travel toTravelListByUser() {
//        return Travel.builder()
//                .travelListByUser(this.travelListByUser)
//                .build();
//    }
}
