package com.dededesignworkshop.seisankun_api.domain.service;

import com.dededesignworkshop.seisankun_api.domain.object.BorrowHistory;
import com.dededesignworkshop.seisankun_api.domain.object.Payment;
import com.dededesignworkshop.seisankun_api.domain.object.PaymentHistory;
import com.dededesignworkshop.seisankun_api.domain.object.User;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.BorrowMoneyEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.PaymentEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.UserEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.BorrowMoneyRepository;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.PaymentRepository;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.TravelRepository;
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

    @NotNull
    private final BorrowMoneyRepository borrowMoneyRepository;

    @NotNull
    private final TravelRepository travelRepository;

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
        payment.setCreatedAt(now_date);
        payment.setUpdatedAt(now_date);
        this.paymentRepository.updatePayment(payment);

        // TODO:削除と更新にトランザクション貼る
        this.borrowMoneyRepository.deleteBorrowRelation(payment.getId());
        payment.setCreatedBy(payment.getUpdatedBy());
        this.addBorrowMoney(payment);

        return this.paymentRepository.findByPaymentId(payment.getId()).map(PaymentEntity::toDomainPayment);
    }

    public void softDeletePayment(Payment payment){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        payment.setUpdatedAt(now_date);
        payment.setDeletedAt(now_date);
        this.paymentRepository.softDeletePayment(payment);
        List<BorrowMoneyEntity> borrowMoneyEntities = this.borrowMoneyRepository.findByPaymentId(payment.getId());
        borrowMoneyEntities.forEach(bme -> {
            bme.setUpdatedBy(payment.getUpdatedBy());
            bme.setUpdatedAt(now_date);
            bme.setDeletedAt(now_date);
            this.borrowMoneyRepository.softDeleteBorrowRelation(bme);
        });
    }

    private void addBorrowMoney(Payment payment){
        Double money = (double)payment.getAmount()/payment.getBorrowers().size();
        payment.getBorrowers().forEach(borrower -> {
            BorrowMoneyEntity borrowMoneyEntity = BorrowMoneyEntity.builder()
                    .paymentId(payment.getId())
                    .borrowerId(borrower)
                    .money(money)
                    .createdAt(payment.getCreatedAt())
                    .createdBy(payment.getCreatedBy())
                    .updatedAt(payment.getUpdatedAt())
                    .updatedBy(payment.getUpdatedBy())
                    .build();
            this.borrowMoneyRepository.createBorrowRelation(borrowMoneyEntity);
        });
    }

    public List<Optional<User>> findBorrowerByPaymentId(Integer paymentId) {
        List<BorrowMoneyEntity> borrowMoneyEntities = this.borrowMoneyRepository.findByPaymentId(paymentId);
        List<Optional<User>> borrowers = new ArrayList<>();
        borrowMoneyEntities.forEach(bme -> {
            borrowers.add(this.userRepository.findByUserId(bme.getBorrowerId()).map(UserEntity::toDomainUser));
        });
        return borrowers;
    }

    public List<BorrowMoneyEntity> findBorrowMoneyByTravelId(Integer travelId) {
		List<PaymentEntity> payments = this.paymentRepository.findByTravelId(travelId);
		List<BorrowMoneyEntity> borrowMoneyEntities = new ArrayList<>();
		payments.forEach(payment -> {
			List<BorrowMoneyEntity> bmeList = this.borrowMoneyRepository.findByPaymentId(payment.getId());
			borrowMoneyEntities.addAll(bmeList);
		});
		return borrowMoneyEntities;
	}

	public List<BorrowHistory> findBorrowHistoryByBorrowerIdAndTravelHashId(Integer borrowerId, String travelHashId) {
        List<BorrowMoneyEntity> borrowMoneyEntityList = this.borrowMoneyRepository.findByBorrowerId(borrowerId);
        Optional<User> user = this.userRepository.findByUserId(borrowerId).map(UserEntity::toDomainUser);
        Integer travelId = this.travelRepository.getTravelId(travelHashId);
        List<BorrowHistory> borrowHistoryList = new ArrayList<>();
        borrowMoneyEntityList.forEach(bme -> {
            Optional<Payment> payment = this.paymentRepository.findByPaymentId(bme.getPaymentId()).map(PaymentEntity::toDomainPayment);
            BorrowHistory borrowHistory = BorrowHistory.builder()
                    .userName(user.get().getName())
                    .paymentTitle(payment.get().getTitle())
                    .build();
            Double borrowMoney;
            if(payment.get().getPayerId() == bme.getBorrowerId()){
                borrowMoney = payment.get().getAmount() - bme.getMoney();
            } else {
                borrowMoney = bme.getMoney() * -1;
            }
            borrowHistory.setBorrowMoney(borrowMoney);
            if (payment.get().getTravelId() == travelId) {
                borrowHistoryList.add(borrowHistory);
            }
        });
        return borrowHistoryList;
    }

}
