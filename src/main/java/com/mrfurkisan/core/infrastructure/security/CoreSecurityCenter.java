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

    public final static CoreSecurityCenter GetSecurity(ITokenService tokenService, IUserService userService) {

        if (__instance == null) {

            __instance = new CoreSecurityCenter(tokenService, userService);
        }
        return __instance;
    }

    public BaseDataResponse<SecurityToken> Verify(FreeDataRequest<LoginForm> loginReq) {

        var loginForm = loginReq.GetData();
        var user = this.__userService.GetUserByEmail(loginForm.emailOrUsername());
        var isEmailJoined = true;
        if (user == null) {

            user = this.__userService.GetUserByUsername(loginForm.emailOrUsername());
            isEmailJoined = false;
        }

        if (user == null) {
            return new ErrorDataResponse<SecurityToken>("Böyle bir kullanici yok");

        }

        if (isEmailJoined) {
            return (user.GetEmail() == loginForm.emailOrUsername())
                    ? new SuccessDataResponse<SecurityToken>("Authenticated", this.__tokenService.CreateToken(user))
                    : new ErrorDataResponse<SecurityToken>("Kullanici adi ya da şifrenizi yanliş girdiniz!");
        }
        return (user.GetUserName() == loginForm.emailOrUsername())
                ? new SuccessDataResponse<SecurityToken>("Authenticated", this.__tokenService.CreateToken(user))
                : new ErrorDataResponse<SecurityToken>("Kullanici adi ya da şifrenizi yanlış girdiniz!");

    }

    public BaseResponse SignIn(FreeDataRequest<RegisterForm> registerReq) {
        var form = registerReq.GetData();
        var user = this.__userService.GetUserByEmail(form.email());
        if (user != null) {
            return new ErrorResponse("Bu emaile kayitli zaten bir kullanici mevcut!");
        }

        var token = this.__userService.CreateUser(registerReq);
        return (token != null)
        ? new SuccessResponse("Kayit tamamlandi") 
        : new ErrorResponse("Kayit tamamlanirken bir hata oluştu!");
    }

    @Override
    public BaseResponse Logout(SecureRequest req) {
        
        var token = this.__tokenService.GetTokenById(req.GetToken());
        if(token == null){
            return new ErrorResponse("Geçersiz Token Kullanildi!");
        }
        return new SuccessResponse("Oturum kapatildi");
    }

    @Override
    public BaseResponse ChangePassword(SecureDataRequest<String> req) {

        SecurityTokenEntity tokenEntity = this.__tokenService.GetTokenById(req.GetToken());

        if (tokenEntity == null) {
            return new ErrorResponse("Cannot Validated Token!");
        }
        var success = this.__userService.ChangePassword(tokenEntity.GetUserId(), req.GetData());
        if (success) {

            this.__tokenService.DeleteToken(tokenEntity);
            return new SuccessResponse("Şifre başariyla değiştirildi!");
        }
        return new ErrorResponse("Şifreniz değiştirilirken bir hata oluştu!");
    }

    @Override
    public BaseResponse ChangeEmail(SecureDataRequest<String> req) {

        SecurityTokenEntity tokenEntity = this.__tokenService.GetTokenById(req.GetToken());

        if (tokenEntity == null) {
            return new ErrorResponse("Cannot Validated Token!");
        }
        var success = this.__userService.ChangeEmail(tokenEntity.GetUserId(), req.GetData());
        if (success) {

            this.__tokenService.DeleteToken(tokenEntity);
            return new SuccessResponse("Email başariyla değiştirildi!");
        }
        return new ErrorResponse("Email değiştirilirken bir hata oluştu!");
    }

    @Override
    public BaseResponse ChangeUserName(SecureDataRequest<String> req) {

        SecurityTokenEntity tokenEntity = this.__tokenService.GetTokenById(req.GetToken());

        if (tokenEntity == null) {
            return new ErrorResponse("Cannot Validated Token!");
        }
        var success = this.__userService.ChangeUsername(tokenEntity.GetUserId(), req.GetData());
        if (success) {

            this.__tokenService.DeleteToken(tokenEntity);
            return new SuccessResponse("Username başariyla değiştirildi!");
        }
        return new ErrorResponse("Username değiştirilirken bir hata oluştu!");
    }
    

}
