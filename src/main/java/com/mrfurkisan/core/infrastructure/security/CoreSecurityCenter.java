package com.mrfurkisan.core.infrastructure.security;

import com.mrfurkisan.core.application.auth.ISecurityCenter;
import com.mrfurkisan.core.application.auth.ITokenService;
import com.mrfurkisan.core.application.auth.IUserService;
import com.mrfurkisan.core.application.forms.LoginForm;
import com.mrfurkisan.core.application.forms.RegisterForm;
import com.mrfurkisan.core.contracts.abstracts.BaseDataResponse;
import com.mrfurkisan.core.contracts.abstracts.BaseResponse;
import com.mrfurkisan.core.contracts.requests.FreeDataRequest;
import com.mrfurkisan.core.contracts.requests.SecureDataRequest;
import com.mrfurkisan.core.contracts.requests.SecureRequest;
import com.mrfurkisan.core.security.authentication.SecurityToken;

public final class CoreSecurityCenter implements ISecurityCenter {

    private final ITokenService __tokenService;
    private final IUserService __userService;

    public CoreSecurityCenter(ITokenService tokenService, IUserService userService) {
        super();
        this.__userService = userService;
        this.__tokenService = tokenService;
    }

    public BaseDataResponse<SecurityToken> Verify(FreeDataRequest<LoginForm> loginReq) {
        
        return null;
    }

    public BaseResponse SignIn(FreeDataRequest<RegisterForm> registerReq) {
        return null;
    }

    @Override
    public BaseResponse Logout(SecureRequest req) {
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Logout'");
    }

    @Override
    public BaseResponse ValidateToken(SecureRequest req) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ValidateToken'");
    }

    @Override
    public BaseResponse ChangePassword(SecureDataRequest<String> req) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ChangePassword'");
    }

    @Override
    public BaseResponse ChangeEmail(SecureDataRequest<String> req) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ChangeEmail'");
    }

    @Override
    public BaseResponse ChangeUserName(SecureDataRequest<String> req) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ChangeUserName'");
    }

}
