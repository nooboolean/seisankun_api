package com.dededesignworkshop.seisankun_api.domain.service;

import com.dededesignworkshop.seisankun_api.domain.object.Travel;
import com.dededesignworkshop.seisankun_api.domain.repository.TravelListByUserRepository;
import com.dededesignworkshop.seisankun_api.domain.repository.UserRepository;
import com.dededesignworkshop.seisankun_api.domain.repository.TravelRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravelService {
    @NonNull
    private final TravelRepository travelRepository;

    @NotNull
    private final TravelListByUserRepository travelListByUserRepository;

    public Optional<Travel> findById(Integer id){
        return this.travelRepository.findById(id);
    }

    public List<Travel> findByUserId(Integer user_id){
        List<Travel> TravelListByUser = this.travelListByUserRepository.findByUserId(user_id);
        System.out.println(TravelListByUser);
        return TravelListByUser;
    }

}
