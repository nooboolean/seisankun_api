package com.dededesignworkshop.seisankun_api.domain.service;

import com.dededesignworkshop.seisankun_api.domain.object.User;
import com.dededesignworkshop.seisankun_api.infrastructure.entity.UserEntity;
import com.dededesignworkshop.seisankun_api.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @NotNull
    final private UserRepository userRepository;

    public void createUserInfo(User user){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String now_date = simpleDateFormat.format(calendar.getTime());
        user.setCreatedAt(now_date);
        user.setUpdatedAt(now_date);
        Integer createdBy = user.getCreatedBy();
        user.setUpdatedBy(createdBy);
        this.userRepository.createUserInfo(user);
    }

    public Optional<User> findByUid(String uid){
        return this.userRepository.findByUid(uid).map(UserEntity::toDomainUser);
    }
}
