package com.dededesignworkshop.seisankun_api.domain.service;

import com.dededesignworkshop.seisankun_api.domain.object.Payment;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.PaymentEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PaymentService {
    @NotNull
    private final PaymentRepository paymentRepository;

    public Stream<Payment> findByTravelId(Integer travel_id) {
        return this.paymentRepository.findByTravelId(travel_id).stream().map(PaymentEntity::toDomainPayment);
    }

    public Optional<Payment> findByPaymentId(Integer payment_id) {
        return this.paymentRepository.findByPaymentId(payment_id).map(PaymentEntity::toDomainPayment);
    }

    public void createPayment(Payment payment){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        payment.setCreatedAt(now_date);
        payment.setUpdatedAt(now_date);
        this.paymentRepository.createPayment(payment);
    }

    public Optional<Payment> updatePayment(Payment payment){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        payment.setUpdatedAt(now_date);
        this.paymentRepository.updatePayment(payment);
        return this.paymentRepository.findByPaymentId(payment.getId()).map(PaymentEntity::toDomainPayment);
    }
}
