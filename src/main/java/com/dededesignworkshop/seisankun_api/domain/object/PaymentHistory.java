package com.dededesignworkshop.seisankun_api.domain.object;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class PaymentHistory {

    private Payment payment;

    private Optional<User> user;
}
