package com.mrfurkisan.core.infrastructure.persistence;



import com.mrfurkisan.core.application.auth.IUserRepository;
import com.mrfurkisan.core.security.authentication.User;

import jakarta.persistence.EntityManager;

public class UserJpaRepository extends CustomJpaRepository<User, Integer> implements IUserRepository {

    public UserJpaRepository(EntityManager manager, Class<User> type) {
        super(manager, type);

    }

}
