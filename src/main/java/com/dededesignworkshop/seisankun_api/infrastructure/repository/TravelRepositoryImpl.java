package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.domain.object.Travel;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.TravelEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TravelRepositoryImpl implements TravelRepository {
    @NonNull
    private final TravelJpaRepository travelJpaRepository;

    @Override
    public Optional<Travel> findById(Integer id) {
        return this.travelJpaRepository.findById(id)
                .map(TravelEntity::toDomainTravel);
    }
}
