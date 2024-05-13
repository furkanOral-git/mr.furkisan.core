package com.mrfurkisan.core.infrastructure.security;



import org.springframework.http.HttpStatusCode;


import com.mrfurkisan.core.application.auth.*;
import com.mrfurkisan.core.application.forms.*;
import com.mrfurkisan.core.contracts.abstracts.*;
import com.mrfurkisan.core.contracts.requests.*;
import com.mrfurkisan.core.contracts.responses.*;
import com.mrfurkisan.core.security.authentication.*;



public final class CoreSecurityCenter implements ISecurityCenter {

    private final ITokenService __tokenService;
    private final IUserService __userService;

    public CoreSecurityCenter(ITokenService tokenService, IUserService userService) {
        super();
        this.__userService = userService;
        this.__tokenService = tokenService;
    }

    public BaseDataResponse<SecurityToken> Verify(FreeDataRequest<LoginForm> loginReq) {
        
        var token = new SecurityToken("uniqueId");
        return new SuccessDataResponse<SecurityToken>("Başarılı",token);
    }

    public BaseResponse SignIn(FreeDataRequest<RegisterForm> registerReq) {
        return null;
    }

    @Override
    public BaseResponse Logout(SecureRequest req) {
        
        return null;
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
