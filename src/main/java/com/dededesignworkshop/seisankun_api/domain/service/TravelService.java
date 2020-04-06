package com.dededesignworkshop.seisankun_api.domain.service;

import com.dededesignworkshop.seisankun_api.domain.object.*;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.TravelEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.UserEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.BorrowMoneyRepository;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.TravelRepository;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.UserRepository;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.UserTravelRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TravelService {
    @NonNull
    private final TravelRepository travelRepository;

    @NotNull
    private final UserTravelRepository userTravelRepository;

    @NotNull
    private final UserRepository userRepository;

    public List<TravelList> findByUserId(Integer user_id) {
        Stream<Travel> travels = this.travelRepository.findByUserId(user_id).stream().map(TravelEntity::toDomainTravelList);
        List<TravelList> travelLists = new ArrayList<TravelList>();
        travels.forEach(travel -> {
            TravelList travelList = TravelList.builder()
                    .travel(travel)
                    .user(this.userRepository.findByTravelId(travel.getId()).stream().map(UserEntity::toDomainUser))
                    .build();
            travelLists.add(travelList);
        });
        return travelLists;
    }

    public Optional<Travel> findByTravelId(Integer travel_id) {
        return this.travelRepository.findByTravelId(travel_id).map(TravelEntity::toDomainTravelList);
    }

    public void createTravel(Travel travel){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        travel.setCreatedAt(now_date);
        travel.setUpdatedAt(now_date);
        Integer createdBy = travel.getCreatedBy();
        travel.setUpdatedBy(createdBy);
        travel.setHashId(UUID.randomUUID().toString());
        this.travelRepository.createTravel(travel);
    }

    public Optional<Travel> updateTravel(Travel travel){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        travel.setUpdatedAt(now_date);
        this.travelRepository.updateTravel(travel);
        return this.travelRepository.findByTravelId(travel.getId()).map(TravelEntity::toDomainTravelList);
    }

    public void softDeleteTravel(Travel travel){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        travel.setUpdatedAt(now_date);
        travel.setDeletedAt(now_date);
        this.travelRepository.softDeleteTravel(travel);
    }

    public void deleteTravel(Integer travelId){
        this.travelRepository.deleteTravel(travelId);
    }

    public void joinTravel(UserTravel userTravel){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        userTravel.setCreatedAt(now_date);
        userTravel.setUpdatedAt(now_date);
        userTravel.setUpdatedBy(userTravel.getCreatedBy());
        this.userTravelRepository.createUserTravel(userTravel);
    }

    public void deleteUserTravel(UserTravel userTravel) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        userTravel.setUpdatedAt(now_date);
        userTravel.setDeletedAt(now_date);
        userTravel.setUpdatedBy(userTravel.getDeletedBy());
        this.userTravelRepository.deleteUserTravel(userTravel);
    }

    public Integer getTravelId(String hash_id) {
        return this.travelRepository.getTravelId(hash_id);
    }

    public UserTravel existTravel(String hashId, String uid) {
        Integer travelId = this.travelRepository.getTravelId(hashId);
        Optional<User> user = this.userRepository.findByUid(uid).map(UserEntity::toDomainUser);
        Integer userId = user.get().getId();
        return this.userTravelRepository.findByUserIdAndTravelId(userId, travelId);
    }

    public void joinTravelByHashId(UserTravelByHashId userTravelByHashId) {
        Integer travelId = this.travelRepository.getTravelId(userTravelByHashId.getTravelHashId());
        Optional<User> user = this.userRepository.findByUid(userTravelByHashId.getUserUid()).map(UserEntity::toDomainUser);
        UserTravel userTravel = UserTravel.builder()
                .travelId(travelId)
                .userId(user.get().getId())
                .createdBy(user.get().getCreatedBy())
                .build();
        this.joinTravel(userTravel);
    }
}
