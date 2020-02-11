package com.dededesignworkshop.seisankun_api.domain.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Travel implements Serializable {

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
