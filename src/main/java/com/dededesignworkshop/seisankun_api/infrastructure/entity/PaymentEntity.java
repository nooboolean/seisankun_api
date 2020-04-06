package com.dededesignworkshop.seisankun_api.infrastructure.entity;


import com.dededesignworkshop.seisankun_api.domain.object.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    private Integer id;

    private Integer travelId;

    private Integer payerId;

    private String title;

    private Integer amount;

    private Integer createdBy;

    private String createdAt;

    private Integer updatedBy;

    private String updatedAt;

    private Integer deletedBy;

    private String deletedAt;

    private Integer deleteFlag;

    public Payment toDomainPayment() {
        return Payment.builder()
                .id(this.id)
                .travelId(this.travelId)
                .payerId(this.payerId)
                .title(this.title)
                .amount(this.amount)
                .createdAt(this.createdAt)
                .createdBy(this.createdBy)
                .updatedAt(this.updatedAt)
                .updatedBy(this.updatedBy)
                .deletedAt(this.deletedAt)
                .deletedBy(this.deletedBy)
                .deleteFlag(this.deleteFlag)
                .build();
    }

}
