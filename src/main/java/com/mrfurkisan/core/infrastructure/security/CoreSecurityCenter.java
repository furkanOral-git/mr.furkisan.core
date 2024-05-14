package com.mrfurkisan.core.infrastructure.security;

import com.mrfurkisan.core.application.auth.*;
import com.mrfurkisan.core.application.forms.*;
import com.mrfurkisan.core.contracts.abstracts.*;
import com.mrfurkisan.core.contracts.requests.*;
import com.mrfurkisan.core.contracts.responses.*;
import com.mrfurkisan.core.security.authentication.*;



public final class CoreSecurityCenter implements ISecurityCenter {

    private final ITokenService __tokenService;
    private final IUserService __userService;
    private static CoreSecurityCenter __instance;

    private CoreSecurityCenter(ITokenService tokenService, IUserService userService) {
        super();
        this.__userService = userService;
        this.__tokenService = tokenService;
    }
    public final static CoreSecurityCenter GetSecurity(ITokenService tokenService, IUserService userService){
        
        if(__instance == null){
            
            __instance = new CoreSecurityCenter(tokenService, userService);
        }
        return __instance;
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
