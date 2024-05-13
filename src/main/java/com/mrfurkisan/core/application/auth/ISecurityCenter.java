package com.mrfurkisan.core.application.auth;

import com.mrfurkisan.core.application.IApplicationServiceCenter;
import com.mrfurkisan.core.application.forms.LoginForm;
import com.mrfurkisan.core.application.forms.RegisterForm;
import com.mrfurkisan.core.contracts.abstracts.BaseDataResponse;
import com.mrfurkisan.core.contracts.abstracts.BaseResponse;
import com.mrfurkisan.core.contracts.requests.FreeDataRequest;
import com.mrfurkisan.core.contracts.requests.SecureDataRequest;
import com.mrfurkisan.core.contracts.requests.SecureRequest;
import com.mrfurkisan.core.security.authentication.SecurityToken;

public interface ISecurityCenter extends IApplicationServiceCenter {

    public BaseDataResponse<SecurityToken> Verify(FreeDataRequest<LoginForm> loginReq);

    public BaseResponse SignIn(FreeDataRequest<RegisterForm> registerReq);

    public BaseResponse Logout(SecureRequest req);

    public BaseResponse ValidateToken(SecureRequest req);

    public BaseResponse ChangePassword(SecureDataRequest<String> req);

    public BaseResponse ChangeEmail(SecureDataRequest<String> req);

    public BaseResponse ChangeUserName(SecureDataRequest<String> req);
}
