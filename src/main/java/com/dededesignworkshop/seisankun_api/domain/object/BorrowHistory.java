package com.dededesignworkshop.seisankun_api.domain.object;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BorrowHistory {

    private String userName;

    private String paymentTitle;

    private Double borrowMoney;
}
