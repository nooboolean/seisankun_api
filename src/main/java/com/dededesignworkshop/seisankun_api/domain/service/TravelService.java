package com.dededesignworkshop.seisankun_api.domain.service;

import com.dededesignworkshop.seisankun_api.domain.object.Travel;
import com.dededesignworkshop.seisankun_api.domain.object.TravelList;
import com.dededesignworkshop.seisankun_api.domain.object.User;
import com.dededesignworkshop.seisankun_api.domain.object.UserTravel;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.BorrowMoneyEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.TravelEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.UserEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.BorrowMoneyRepository;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.TravelRepository;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.UserRepository;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.UserTravelRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @NotNull
    private final UserService userService;

    @NotNull
    private final BorrowMoneyRepository borrowMoneyRepository;

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

        // TODO:不要処理のため　後ほど削除
//        List<User> users = this.userService.findByTravelId(userTravel.getTravelId());
//        this.addBorrowRelation(userTravel, users);
//        List<BorrowMoneyEntity> borrowMoneyList = this.createBorrowRelationList(userTravel, users);
//        borrowMoneyList.forEach(borrowMoneyEntity -> {
//            this.borrowMoneyRepository.createBorrowRelation(borrowMoneyEntity);
//        });
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

    // TODO:不要処理のため　後ほど削除
//    private void addBorrowRelation(UserTravel userTravel, List<User> users){
//        users.forEach(user -> {
//            if(user.getId().equals(userTravel.getUserId())) return;
//            BorrowMoneyEntity addBorrowMoney = new BorrowMoneyEntity();
//            addBorrowMoney = addBorrowMoney.builder()
//                    .travelId(userTravel.getTravelId())
//                    .borrowerId(user.getId())
//                    .lenderId(userTravel.getUserId())
//                    .money(0)
//                    .createdBy(userTravel.getCreatedBy())
//                    .createdAt(userTravel.getCreatedAt())
//                    .updatedBy(userTravel.getUpdatedBy())
//                    .updatedAt(userTravel.getUpdatedAt())
//                    .build();
//            this.borrowMoneyRepository.createBorrowRelation(addBorrowMoney);
//        });
//    }
//        // TODO:不要処理のため　後ほど削除
//    private List<BorrowMoneyEntity> createBorrowRelationList(UserTravel userTravel , List<User> users){
//        List<BorrowMoneyEntity> borrowMoneyList = new ArrayList<>();
//        users.forEach(user -> {
//                BorrowMoneyEntity borrowMoney = new BorrowMoneyEntity();
//                borrowMoney = borrowMoney.builder()
//                        .travelId(userTravel.getTravelId())
//                        .borrowerId(userTravel.getUserId())
//                        .lenderId(user.getId())
//                        .money(0)
//                        .createdBy(userTravel.getCreatedBy())
//                        .createdAt(userTravel.getCreatedAt())
//                        .updatedBy(userTravel.getUpdatedBy())
//                        .updatedAt(userTravel.getUpdatedAt())
//                        .build();
//                borrowMoneyList.add(borrowMoney);
//        });
//        return borrowMoneyList;
//    }
}
