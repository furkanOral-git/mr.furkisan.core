package com.mrfurkisan.core.infrastructure.services;

import com.mrfurkisan.core.application.auth.IUserRepository;
import com.mrfurkisan.core.application.auth.IUserService;
import com.mrfurkisan.core.application.forms.RegisterForm;
import com.mrfurkisan.core.contracts.abstracts.BaseDataResponse;
import com.mrfurkisan.core.contracts.abstracts.BaseResponse;
import com.mrfurkisan.core.contracts.requests.FreeDataRequest;
import com.mrfurkisan.core.security.authentication.SecurityToken;
import com.mrfurkisan.core.security.authentication.User;

public final class UserService<TRepository extends IUserRepository> implements IUserService {
    
    private TRepository __repository;
    
    public UserService(TRepository repository) {
        super();
        this.__repository = repository;
    }
    @Override
    public BaseDataResponse<SecurityToken> CreateUser(FreeDataRequest<RegisterForm> user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CreateUser'");
    }

    @Override
    public BaseResponse DeleteUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DeleteUser'");
    }

    @Override
    public BaseResponse ChangePassword(User user, String newPassword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ChangePassword'");
    }

    @Override
    public BaseResponse ChangeEmail(User user, String newEmail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ChangeEmail'");
    }

    @Override
    public BaseResponse ChangeUsername(User user, String newUserName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ChangeUsername'");
    }

    @Override
    public BaseDataResponse<User> GetUserByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GetUserByEmail'");
    }

    @Override
    public BaseDataResponse<User> GetUserByUsername(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GetUserByUsername'");
    }

}
