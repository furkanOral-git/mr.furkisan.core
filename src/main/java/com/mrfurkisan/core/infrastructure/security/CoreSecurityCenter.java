package com.mrfurkisan.core.infrastructure.security;

import com.mrfurkisan.core.application.auth.*;
import com.mrfurkisan.core.application.forms.*;
import com.mrfurkisan.core.contracts.abstracts.*;
import com.mrfurkisan.core.contracts.requests.*;
import com.mrfurkisan.core.contracts.responses.*;
import com.mrfurkisan.core.infrastructure.services.AuthorizationService;
import com.mrfurkisan.core.security.authentication.*;
import com.mrfurkisan.core.security.authorization.*;

public final class CoreSecurityCenter implements ISecurityCenter {

    private final ITokenService __tokenService;
    private final IUserService __userService;
    private final AuthorizationService __authorManager;

    public CoreSecurityCenter(ITokenService tokenService, IUserService userService, IAuthorizationService builder) {
        super();
        this.__userService = userService;
        this.__tokenService = tokenService;
        this.__authorManager = (AuthorizationService) builder;

    }

    public BaseDataResponse<SecurityToken> Verify(FreeDataRequest<LoginForm> loginReq) {

        var loginForm = loginReq.GetData();
        var user = this.__userService.GetUserByEmail(loginForm.emailOrUsername());
        var isEmailEntered = true;
        if (user == null) {

            user = this.__userService.GetUserByUsername(loginForm.emailOrUsername());
            isEmailEntered = false;
            if (user == null) {

                return new ErrorDataResponse<SecurityToken>("Böyle bir kullanici yok");

            }
        }
        // Ternary
        String userEmailOrUsername = (isEmailEntered) ? user.getEmail() : user.getUsername();
        // == operatörü hata verdi
        return (userEmailOrUsername.equals(loginForm.emailOrUsername())
                && user.getPassword().equals(loginForm.password()))
                        ? new SuccessDataResponse<SecurityToken>("Authenticated",
                                this.__tokenService.CreateToken(user.getUser_id(), user.getRole_id(),
                                        loginForm.macAddress()))
                        : new ErrorDataResponse<SecurityToken>("Kullanici adi ya da şifrenizi yanliş girdiniz!");

    }

    public BaseResponse SignIn(FreeDataRequest<RegisterForm> registerReq) {

        var form = registerReq.GetData();
        var user = this.__userService.GetUserByEmail(form.email());
        if (user != null) {
            return new ErrorResponse("Bu emaile kayitli zaten bir kullanici mevcut!");
        }
        // AccessLevel.Authenticated standart giriş yapmış kullanıcı seviyesi olarak
        // belirleniyor.
        var authedRole = this.__authorManager.GetBy((role) -> role.getLevel() == AccessLevel.Authenticated);
        var isOk = this.__userService.CreateUser(registerReq.GetData(), authedRole.getRole_id());
        return (isOk == true)
                ? new SuccessResponse("Kayit tamamlandi")
                : new ErrorResponse("Kayit tamamlanirken bir hata oluştu!");
    }

    public BaseResponse Logout(SecureRequest req) {

        var token = this.Validate(req.GetToken().GetId());
        if (token == null) {
            return new ErrorResponse("Geçersiz Token Kullanildi!");
        }
        return new SuccessResponse("Oturum kapatildi");
    }

    public BaseResponse ChangePassword(SecureDataRequest<String> req) {

        SecurityTokenEntity tokenEntity = this.Validate(req.GetToken().GetId());
        if (tokenEntity == null) {
            return new ErrorResponse("Cannot Validated Token!");
        }
        
        User user = this.__userService.GetUserById(tokenEntity.getUser_id());
        if (user.getPassword().equals(req.GetData())) {
            return new ErrorResponse("Yeni şifreniz bir öncekiyle ayni olamaz!");
        }

        var success = this.__userService.ChangePassword(tokenEntity.getUser_id(), req.GetData());
        if (success) {

            this.__tokenService.DeleteToken(tokenEntity);
            return new SuccessResponse("Şifre başariyla değiştirildi!");
        }
        return new ErrorResponse("Şifreniz değiştirilirken bir hata oluştu!");
    }

    public BaseResponse ChangeEmail(SecureDataRequest<String> req) {

        SecurityTokenEntity tokenEntity = this.Validate(req.GetToken().GetId());

        if (tokenEntity == null) {

            return new ErrorResponse("Cannot Validated Token!");
        }
        var searchResult = this.__userService.GetUserByEmail(req.GetData());
        if (searchResult != null) {
            return new ErrorResponse("Girdiğiniz email zaten kayitli!");
        }
        var success = this.__userService.ChangeEmail(tokenEntity.getUser_id(), req.GetData());
        if (success) {

            this.__tokenService.DeleteToken(tokenEntity);
            return new SuccessResponse("Email başariyla değiştirildi!");
        }
        return new ErrorResponse("Email değiştirilirken bir hata oluştu!");
    }

    private SecurityTokenEntity Validate(String id) {

        // Authentication doğrulama işlemleri burada yapılacak
        SecurityTokenEntity tokenEntity = this.__tokenService.GetEntityByTokenId(id);
        /*
         * ilerleyen zamanlarda mac adresi doğrulama, eğer eşit değilse email ve şifre
         * isteme gibi güvenliği kanıtlamaya dayalı işlemler burada yer alacak.
         * email gönderme,sms gönderme etc.
         */
        return tokenEntity;

    }

    public BaseResponse ChangeUserName(SecureDataRequest<String> req) {
        // Authenticate olmuş mu bu kontrol ediliyor.
        SecurityTokenEntity tokenEntity = this.Validate(req.GetToken().GetId());

        if (tokenEntity == null) {
            return new ErrorResponse("Cannot Validated Token!");
        }
        var searchResult = this.__userService.GetUserByUsername(req.GetData());
        if (searchResult != null) {
            return new ErrorResponse("Girdiğiniz kullanici adi kullaniliyor!");
        }
        var success = this.__userService.ChangeUsername(tokenEntity.getUser_id(), req.GetData());
        if (success) {

            this.__tokenService.DeleteToken(tokenEntity);
            return new SuccessResponse("Username başariyla değiştirildi!");
        }
        return new ErrorResponse("Username değiştirilirken bir hata oluştu!");
    }

    public BaseResponse AuthorizationValidation(SecureRequest req) {

        SecurityTokenEntity tokenEntity = this.Validate(req.GetToken().GetId());

        if (tokenEntity == null) {
            return new ErrorResponse("Cannot Validated Token!");
        }
        // db sorguları
        Role role = this.__authorManager.GetBy((e) -> e.getRole_id() == tokenEntity.getRole_id());

        // Authorization'a özel bussiness logic işlemleri.
        return CoreSecurityAuthorizationLogic.ValidateAuthority(role, req.GetRequestType());

    }

}
