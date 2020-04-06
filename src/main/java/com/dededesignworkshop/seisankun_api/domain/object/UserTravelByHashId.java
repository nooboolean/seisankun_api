package com.dededesignworkshop.seisankun_api.domain.object;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserTravelByHashId {

    private String travelHashId;

    private String userUid;
}
