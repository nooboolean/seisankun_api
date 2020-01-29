package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.domain.object.Travel;
import com.dededesignworkshop.seisankun_api.domain.repository.TravelListByUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TravelListByUserRepositoryImpl implements TravelListByUserRepository {

    @NotNull
    private final TravelListByUserJpaRepository travelListByUserJpaRepository;

    @Override
    public List<Travel> findByUserId(Integer user_id){
        return this.travelListByUserJpaRepository.findById(user_id).get().getTravelListByUser();
    }

}
