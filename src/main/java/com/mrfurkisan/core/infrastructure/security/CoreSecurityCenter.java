package com.mrfurkisan.core.infrastructure.security;

import com.mrfurkisan.core.application.auth.*;
import com.mrfurkisan.core.application.forms.*;
import com.mrfurkisan.core.contracts.abstracts.*;
import com.mrfurkisan.core.contracts.requests.*;
import com.mrfurkisan.core.contracts.responses.*;
import com.mrfurkisan.core.security.authentication.*;
import com.mrfurkisan.core.security.authorization.*;

public final class CoreSecurityCenter implements ISecurityCenter {

    private final ITokenService __tokenService;
    private final IUserService __userService;
    private static CoreSecurityCenter __instance;
    private final AuthorizationManager __authorManager;

    private CoreSecurityCenter(ITokenService tokenService, IUserService userService) {
        super();
        this.__userService = userService;
        this.__tokenService = tokenService;
        this.__authorManager = AuthorizationManager.GetInstance();
    }

    public final static CoreSecurityCenter GetSecurity(ITokenService tokenService, IUserService userService) {

        if (__instance == null) {

            __instance = new CoreSecurityCenter(tokenService, userService);
        }
        return __instance;
    }

    public BaseDataResponse<SecurityToken> Verify(FreeDataRequest<LoginForm> loginReq) {

        var loginForm = loginReq.GetData();
        var user = this.__userService.GetUserByEmail(loginForm.getEmailOrUsername());
        var isEmailJoined = true;
        if (user == null) {

            user = this.__userService.GetUserByUsername(loginForm.getEmailOrUsername());
            isEmailJoined = false;
        }

        if (user == null) {
            return new ErrorDataResponse<SecurityToken>("Böyle bir kullanici yok");

        }

        if (isEmailJoined) {
            return (user.GetEmail() == loginForm.getEmailOrUsername())
                    ? new SuccessDataResponse<SecurityToken>("Authenticated", this.__tokenService.CreateToken(user))
                    : new ErrorDataResponse<SecurityToken>("Kullanici adi ya da şifrenizi yanliş girdiniz!");
        }
        return (user.GetUserName() == loginForm.getEmailOrUsername())
                ? new SuccessDataResponse<SecurityToken>("Authenticated", this.__tokenService.CreateToken(user))
                : new ErrorDataResponse<SecurityToken>("Kullanici adi ya da şifrenizi yanlış girdiniz!");

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
        var isOk = this.__userService.CreateUser(registerReq.GetData(), (int) authedRole.GetId());
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
        var success = this.__userService.ChangePassword(tokenEntity.GetUserId(), req.GetData());
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
        var success = this.__userService.ChangeEmail(tokenEntity.GetUserId(), req.GetData());
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
        var success = this.__userService.ChangeUsername(tokenEntity.GetUserId(), req.GetData());
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

        Role role = this.__authorManager.GetBy((e) -> (int) e.GetId() == tokenEntity.GetRoleId());

        AuthorityDetails details = CoreSecurityLogicService.CheckAuthorityAnnotations();
        if (details.classLevel() == null && details.methodLevel() == null) {
            return new ErrorResponse(
                    "Internal Error : Check the authority configuration of controllers, that error occurs on missing annotation declaring!");
        }

        /*
         * Class level kontrolü
         * Kural : MethodLevel > ClassLevel
         * Yani öncelikli olarak method level kurallarından geçmiş olması gerekiyor
         * .
         */

        Authority classLevel = details.classLevel();
        Authority methodLevel = details.methodLevel();

        if (classLevel == null) {

            return CoreSecurityLogicService.IsValidForAuthority(role, methodLevel, req.GetRequestType());
        }
        if (methodLevel == null) {

            return CoreSecurityLogicService.IsValidForAuthority(role, classLevel, req.GetRequestType());
        }
        if (methodLevel != null && classLevel != null) {

            return CoreSecurityLogicService.IsValidForAuthority(role, methodLevel, req.GetRequestType());
        }
        return new ErrorResponse("Not Authorized!");

    }

    public static IAuthorizationBuilder AuthorizationBuilder() {

        return AuthorizationManager.GetInstance();
    }

}
