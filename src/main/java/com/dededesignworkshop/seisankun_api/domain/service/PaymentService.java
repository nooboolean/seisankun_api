package com.dededesignworkshop.seisankun_api.domain.service;

import com.dededesignworkshop.seisankun_api.domain.object.Payment;
import com.dededesignworkshop.seisankun_api.domain.object.PaymentHistory;
import com.dededesignworkshop.seisankun_api.domain.object.User;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.PaymentEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.UserEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.PaymentRepository;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PaymentService {
    @NotNull
    private final PaymentRepository paymentRepository;

    @NotNull
    private final UserRepository userRepository;

    public List<PaymentHistory> findByTravelId(Integer travel_id) {
        Stream<Payment> payments = this.paymentRepository.findByTravelId(travel_id).stream().map(PaymentEntity::toDomainPayment);
        List<PaymentHistory> paymentHistories = new ArrayList<PaymentHistory>();
        payments.forEach(payment -> {
            PaymentHistory paymentHistory = PaymentHistory.builder()
                    .payment(payment)
                    .user(this.userRepository.findByUserId(payment.getPayerId()).map(UserEntity::toDomainUser))
                    .build();
            paymentHistories.add(paymentHistory);
        });
        return paymentHistories;
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
        this.addBorrowMoney(payment);
    }

    public Optional<Payment> updatePayment(Payment payment){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        payment.setUpdatedAt(now_date);
        this.paymentRepository.updatePayment(payment);
        return this.paymentRepository.findByPaymentId(payment.getId()).map(PaymentEntity::toDomainPayment);
    }

    public void softDeletePayment(Payment payment){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        payment.setUpdatedAt(now_date);
        payment.setDeletedAt(now_date);
        this.paymentRepository.softDeletePayment(payment);
    }

    private void addBorrowMoney(Payment payment){
        
    }
}
