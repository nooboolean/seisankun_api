package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.infrastructure.entity.PaymentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PaymentRepository {
    @Select("SELECT * FROM payments WHERE travel_id = #{travel_id}")
    List<PaymentEntity> findByTravelId(@Param("travel_id") Integer travel_id);

    @Select("SELECT * FROM payments WHERE id = #{payment_id}")
    Optional<PaymentEntity> findByPaymentId(@Param("payment_id") Integer payment_id);
}
