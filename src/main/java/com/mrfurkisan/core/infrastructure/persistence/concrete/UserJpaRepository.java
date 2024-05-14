package com.mrfurkisan.core.infrastructure.persistence.concrete;



import com.mrfurkisan.core.application.auth.IUserRepository;
import com.mrfurkisan.core.infrastructure.persistence.BaseJpaRepository;
import com.mrfurkisan.core.security.authentication.User;

import jakarta.persistence.EntityManager;

public class UserJpaRepository extends BaseJpaRepository<User, Integer> implements IUserRepository {

    public UserJpaRepository(EntityManager manager, Class<User> type) {
        super(manager, type);

    }

}
