package com.mrfurkisan.core.infrastructure.services;

import java.util.UUID;



import com.mrfurkisan.core.application.auth.IUserRepository;
import com.mrfurkisan.core.application.auth.IUserService;
import com.mrfurkisan.core.application.forms.RegisterForm;
import com.mrfurkisan.core.domain.functional.IJpaFunctionalInterface;
import com.mrfurkisan.core.infrastructure.persistence.concrete.UserJpaRepository;
import com.mrfurkisan.core.security.authentication.User;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;



public final class UserService<TRepository extends IUserRepository> implements IUserService {

    private TRepository __repository;

    public UserService(TRepository repository) {
        super();
        this.__repository = repository;
    }

    @Override
    public Boolean CreateUser(RegisterForm form, String roleId) {

        var initialUserName = String.format("User-%s", UUID.randomUUID().toString().replace("-", ""));

        User user = new User();
        user.setEmail(form.email());
        user.setPassword(form.password());
        user.setRole_id(roleId);
        user.setDefault_mac_address(form.macAddress());
        user.setUsername(initialUserName);

        if (this.__repository instanceof UserJpaRepository) {
            var castedRepo = (UserJpaRepository) __repository;
            castedRepo.Add(user);
            return true;
        }
        return false;
    }

    @Override
    public Boolean DeleteUser(int userId) {

        if (this.__repository instanceof UserJpaRepository) {

            var castedRepo = (UserJpaRepository) this.__repository;

            IJpaFunctionalInterface<User, CriteriaDelete<User>> filter = (CriteriaBuilder builder) -> {

                CriteriaDelete<User> delete = builder.createCriteriaDelete(User.class);
                Root<User> table = delete.from(User.class);

                delete.from(User.class);

                delete.where(builder.equal(table.get("user_id"), userId));
                return delete;
            };
            castedRepo.DeleteBy(filter);
            return true;
        }
        return false;
    }

    @Override
    public Boolean ChangePassword(int userId, String newPassword) {

        if (this.__repository instanceof UserJpaRepository) {

            var castedRepo = (UserJpaRepository) this.__repository;

            IJpaFunctionalInterface<User, CriteriaUpdate<User>> filter = (CriteriaBuilder builder) -> {

                CriteriaUpdate<User> update = builder.createCriteriaUpdate(User.class);
                Root<User> userTable = update.from(User.class);

                update.set("password", newPassword);
                update.where(builder.equal(userTable.get("user_id"), userId));

                return update;

            };
            castedRepo.UpdateBy(filter);

            return true;

        }
        return false;
    }

    @Override
    public Boolean ChangeEmail(int userId, String newEmail) {

        if (this.__repository instanceof UserJpaRepository) {

            var castedRepo = (UserJpaRepository) this.__repository;

            IJpaFunctionalInterface<User, CriteriaUpdate<User>> filter = (CriteriaBuilder builder) -> {

                CriteriaUpdate<User> update = builder.createCriteriaUpdate(User.class);
                Root<User> userTable = update.from(User.class);

                update.set("email", newEmail);
                update.where(builder.equal(userTable.get("user_id"), userId));

                return update;

            };
            castedRepo.UpdateBy(filter);

            return true;

        }
        return false;
    }

    @Override
    public Boolean ChangeUsername(int userId, String newUserName) {

        if (this.__repository instanceof UserJpaRepository) {

            var castedRepo = (UserJpaRepository) this.__repository;

            IJpaFunctionalInterface<User, CriteriaUpdate<User>> filter = (CriteriaBuilder builder) -> {

                CriteriaUpdate<User> update = builder.createCriteriaUpdate(User.class);
                Root<User> userTable = update.from(User.class);

                update.set("username", newUserName);
                update.where(builder.equal(userTable.get("user_id"), userId));

                return update;

            };
            castedRepo.UpdateBy(filter);
        }
        return true;
    }

    @Override
    public User GetUserByEmail(String email) {

        User entity = null;

        if (this.__repository instanceof UserJpaRepository) {
            var castedRepo = (UserJpaRepository) this.__repository;

            IJpaFunctionalInterface<User, CriteriaQuery<User>> filter = (CriteriaBuilder builder) -> {

                CriteriaQuery<User> query = builder.createQuery(User.class);
                Root<User> table = query.from(User.class);

                // tabloda email kolonunu al ve email ile eşit mi diye kontrol et
                Predicate predicate = builder.equal(table.get("email"), email);

                query.where(predicate);
                return query;
            };

            entity = castedRepo.GetBy(filter);
        }
        return entity;
    }

    @Override
    public User GetUserByUsername(String userName) {

        User entity = null;

        if (this.__repository instanceof UserJpaRepository) {
            var castedRepo = (UserJpaRepository) this.__repository;

            IJpaFunctionalInterface<User, CriteriaQuery<User>> filter = (CriteriaBuilder builder) -> {

                CriteriaQuery<User> query = builder.createQuery(User.class);
                Root<User> table = query.from(User.class);

                // tabloda email kolonunu al ve email ile eşit mi diye kontrol et
                Predicate predicate = builder.equal(table.get("username"), userName);

                query.where(predicate);
                return query;
            };

            entity = castedRepo.GetBy(filter);
        }
        return entity;
    }

    @Override
    public User GetUserById(int id) {
        
        User entity = null;
        if (this.__repository instanceof UserJpaRepository) {

            var casted = (UserJpaRepository) this.__repository;

            IJpaFunctionalInterface<User, CriteriaQuery<User>> filter = (CriteriaBuilder builder) -> {
                CriteriaQuery<User> query = builder.createQuery(User.class);
                Root<User> table = query.from(User.class);

                Predicate pre = builder.equal(table.get("user_id"), id);
                query.where(pre);

                return query;
            };
            entity = casted.GetBy(filter);
        }
        return entity;
    }

}
