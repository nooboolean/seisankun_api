package com.dededesignworkshop.seisankun_api.infrastructure.repository;

import com.dededesignworkshop.seisankun_api.domain.object.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Integer id);
}
