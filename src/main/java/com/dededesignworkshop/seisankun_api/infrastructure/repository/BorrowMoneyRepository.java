package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.infrastructure.entity.BorrowMoneyEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BorrowMoneyRepository {

    @Insert("INSERT INTO borrow_money (travel_id, borrower_id, lender_id, money, created_by, created_at, updated_by, updated_at)" +
            "VALUES (#{travelId}, #{borrowerId}, #{lenderId}, #{money}, #{createdBy}, #{createdAt}, #{updatedBy}, #{updatedAt})")
    void createBorrowRelation(BorrowMoneyEntity borrowMoneyEntity);
}
