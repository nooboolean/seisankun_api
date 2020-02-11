package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.domain.object.Payment;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.PaymentEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PaymentRepository {
    @Select("SELECT * FROM payments WHERE travel_id = #{travel_id}")
    List<PaymentEntity> findByTravelId(@Param("travel_id") Integer travel_id);

    @Select("SELECT * FROM payments WHERE id = #{payment_id}")
    Optional<PaymentEntity> findByPaymentId(@Param("payment_id") Integer payment_id);

    @Insert("INSERT INTO payments (travel_id, payer_id, amount, created_by, created_at, updated_by, updated_at)" +
            "VALUES (#{travelId}, #{payerId}, #{amount}, #{createdBy}, #{createdAt}, #{updatedBy}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createPayment(Payment payment);
}
