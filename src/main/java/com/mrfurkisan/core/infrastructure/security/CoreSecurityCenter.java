package com.mrfurkisan.core.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;

import com.mrfurkisan.core.application.auth.*;
import com.mrfurkisan.core.application.forms.*;
import com.mrfurkisan.core.contracts.abstracts.*;
import com.mrfurkisan.core.contracts.requests.*;
import com.mrfurkisan.core.contracts.responses.*;
import com.mrfurkisan.core.infrastructure.security.messages.SessionErrorMessages;
import com.mrfurkisan.core.infrastructure.security.messages.SessionSuccessMessages;
import com.mrfurkisan.core.security.authentication.*;
import com.mrfurkisan.core.security.authorization.*;

public final class CoreSecurityCenter implements ISecurityCenter {

    @Autowired
    private final ITokenService __tokenService;
    @Autowired
    private final IUserService __userService;
    @Autowired
    private final IAuthorizationService __authorManager;

    public CoreSecurityCenter(ITokenService tokenService, IUserService userService, IAuthorizationService builder) {
        super();
        this.__userService = userService;
        this.__tokenService = tokenService;
        this.__authorManager = builder;

    }

    public BaseDataResponse<SecurityToken> Login(FreeDataRequest<LoginForm> loginReq) {

        var loginForm = loginReq.GetData();
        var user = this.__userService.GetUserByEmail(loginForm.emailOrUsername());

        var isEmailEntered = true;
        if (user == null) {

            user = this.__userService.GetUserByUsername(loginForm.emailOrUsername());
            isEmailEntered = false;
            if (user == null) {

                return new ErrorDataResponse<SecurityToken>(SessionErrorMessages.NotExistUsernameOrEmail);

            }
        }
        // Açık oturum varsa kapatılıyor.
        this.__tokenService.DeleteByUserId(user.getUser_id());
        // Ternary
        String userEmailOrUsername = (isEmailEntered) ? user.getEmail() : user.getUsername();
        // == operatörü hata verdi
        return (userEmailOrUsername.equals(loginForm.emailOrUsername())
                && user.getPassword().equals(loginForm.password()))
                        ? new SuccessDataResponse<SecurityToken>(SessionSuccessMessages.Login,
                                this.__tokenService.CreateToken(user.getUser_id(), user.getRole_id(),
                                        loginForm.macAddress()))
                        : new ErrorDataResponse<SecurityToken>(SessionErrorMessages.NotAuhenticated);

    }

    public BaseResponse SignIn(FreeDataRequest<RegisterForm> registerReq) {

        var form = registerReq.GetData();
        var user = this.__userService.GetUserByEmail(form.email());
        if (user != null) {
            return new ErrorResponse(SessionErrorMessages.ExistEmail);
        }

        Role authedRole = this.__authorManager.GetRoleByDomain(DomainName.NORMAL);
        var isOk = this.__userService.CreateUser(registerReq.GetData(), authedRole.getId());
        return (isOk == true)
                ? new SuccessResponse(SessionSuccessMessages.SignIn)
                : new ErrorResponse(SessionErrorMessages.SignIn);
    }

    public BaseResponse Logout(SecureRequest req) {

        BaseDataResponse<SecurityTokenEntity> validationResult = this.BaseValidateAccessRequest(req.GetToken().GetId(),
                req.GetRequestType());

        if (!validationResult.GetSuccess()) {
            return validationResult;
        }

        SecurityTokenEntity tokenEntity = validationResult.GetData();

        this.__tokenService.DeleteToken(tokenEntity);

        return new SuccessResponse(SessionSuccessMessages.Logout);
    }

    public BaseResponse ChangePassword(SecureDataRequest<String> req) {

        BaseDataResponse<SecurityTokenEntity> validationResult = this.BaseValidateAccessRequest(req.GetToken().GetId(),
                req.GetRequestType());

        if (!validationResult.GetSuccess()) {
            return validationResult;
        }
        SecurityTokenEntity tokenEntity = validationResult.GetData();

        User user = this.__userService.GetUserById(tokenEntity.getUser_id());
        if (user.getPassword().equals(req.GetData())) {
            return new ErrorResponse(SessionErrorMessages.SamePassword);
        }

        var success = this.__userService.ChangePassword(tokenEntity.getUser_id(), req.GetData());
        if (success) {

            return new SuccessResponse(SessionSuccessMessages.ChangePassword);
        }
        return new ErrorResponse(SessionErrorMessages.ChangePassword);
    }

    public BaseResponse ChangeEmail(SecureDataRequest<String> req) {

        BaseDataResponse<SecurityTokenEntity> validationResult = this.BaseValidateAccessRequest(req.GetToken().GetId(),
                req.GetRequestType());

        if (!validationResult.GetSuccess()) {
            return validationResult;
        }

        SecurityTokenEntity tokenEntity = validationResult.GetData();
        var searchResult = this.__userService.GetUserByEmail(req.GetData());

        if (searchResult != null) {
            return new ErrorResponse(SessionErrorMessages.ExistEmail);
        }
        var success = this.__userService.ChangeEmail(tokenEntity.getUser_id(), req.GetData());
        if (success) {

            this.__tokenService.DeleteToken(tokenEntity);
            return new SuccessResponse(SessionSuccessMessages.ChangeEmail);
        }
        return new ErrorResponse(SessionErrorMessages.ChangeEmail);
    }

    public BaseResponse ChangeUserName(SecureDataRequest<String> req) {
        // Authenticate olmuş mu bu kontrol ediliyor.

        BaseDataResponse<SecurityTokenEntity> validationResult = this.BaseValidateAccessRequest(req.GetToken().GetId(),
                req.GetRequestType());

        if (!validationResult.GetSuccess()) {
            return validationResult;
        }

        SecurityTokenEntity tokenEntity = validationResult.GetData();

        var searchResult = this.__userService.GetUserByUsername(req.GetData());
        if (searchResult != null) {
            return new ErrorResponse(SessionErrorMessages.ExistUsername);
        }
        var success = this.__userService.ChangeUsername(tokenEntity.getUser_id(), req.GetData());
        if (success) {

            return new SuccessResponse(SessionSuccessMessages.ChangeUsername);
        }
        return new ErrorResponse(SessionErrorMessages.ChangeUsername);
    }

    public BaseResponse ValidateAccessRequest(SecureRequest req) {

        BaseDataResponse<SecurityTokenEntity> result = this.BaseValidateAccessRequest(req.GetToken().GetId(),
                req.GetRequestType());
        return result;
    }

    private BaseDataResponse<SecurityTokenEntity> BaseValidateAccessRequest(String token_id, RequestType reqType) {

        SecurityTokenEntity tokenEntity = this.__tokenService.GetEntityByTokenId(token_id);

        if (tokenEntity == null) {
            return new ErrorDataResponse<SecurityTokenEntity>(SessionErrorMessages.NotAuhenticated);
        }

        Role role = this.__authorManager.GetRoleById(tokenEntity.getRole_id());

        // Authorization'a özel bussiness logic işlemleri.
        BaseResponse authorizationResults = CoreSecurityAuthorizationLogic.ValidateAuthority(role, reqType);
        if (authorizationResults.GetSuccess()) {
            return new SuccessDataResponse<SecurityTokenEntity>(SessionSuccessMessages.Authorized, tokenEntity);
        }
        return new ErrorDataResponse<SecurityTokenEntity>(SessionErrorMessages.NotAuthorized);
    }

}
