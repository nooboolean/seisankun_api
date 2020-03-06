package com.dededesignworkshop.seisankun_api.domain.object;

import lombok.Builder;
import lombok.Data;

import java.util.stream.Stream;

@Data
@Builder
public class TravelList {

    private Travel travel;

    private Stream<User> user;
}
