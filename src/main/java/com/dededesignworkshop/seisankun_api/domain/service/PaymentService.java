package com.dededesignworkshop.seisankun_api.domain.service;

import com.dededesignworkshop.seisankun_api.domain.object.Payment;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.PaymentEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PaymentService {
    @NotNull
    private final PaymentRepository paymentRepository;

    public Stream<Payment> findByTravelId(Integer travel_id) {
        return this.paymentRepository.findByTravelId(travel_id).stream().map(PaymentEntity::toDomainPayment);
    }
}
