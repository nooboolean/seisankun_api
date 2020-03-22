package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.domain.object.Payment;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.BorrowMoneyEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BorrowMoneyRepository {

    @Insert("INSERT INTO borrow_money (payment_id, borrower_id, money, created_by, created_at, updated_by, updated_at)" +
            "VALUES (#{paymentId}, #{borrowerId}, #{money}, #{createdBy}, #{createdAt}, #{updatedBy}, #{updatedAt})")
    void createBorrowRelation(BorrowMoneyEntity borrowMoneyEntity);

    @Select("SELECT * FROM borrow_money WHERE payment_id = #{paymentId}")
    List<BorrowMoneyEntity> findByPaymentId(@Param("paymentId") Integer paymentId);

    @Update("UPDATE borrow_money SET money = #{money}, updated_by = #{updatedBy}, updated_at = #{updatedAt}" +
            "WHERE id = #{id}")
    void updateBorrowMoney(BorrowMoneyEntity borrowMoneyEntity);

    @Update("UPDATE borrow_money SET updated_by = #{updatedBy}, updated_at = #{updatedAt}, deleted_by = #{updatedBy}, deleted_at = #{deletedAt}," +
            "delete_flag = 1 WHERE id = #{id}")
    void softDeleteBorrowRelation(BorrowMoneyEntity borrowMoneyEntity);

    @Delete("DELETE FROM borrow_money WHERE payment_id = #{paymentId}")
    void deleteBorrowRelation(@Param("paymentId") Integer paymentId);

}
