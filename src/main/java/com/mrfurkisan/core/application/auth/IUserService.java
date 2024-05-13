package com.mrfurkisan.core.application.auth;

import com.mrfurkisan.core.application.IApplicationService;
import com.mrfurkisan.core.application.forms.RegisterForm;
import com.mrfurkisan.core.contracts.abstracts.BaseDataResponse;
import com.mrfurkisan.core.contracts.abstracts.BaseResponse;
import com.mrfurkisan.core.contracts.requests.FreeDataRequest;
import com.mrfurkisan.core.security.authentication.SecurityToken;
import com.mrfurkisan.core.security.authentication.User;

public interface IUserService extends IApplicationService {

    public BaseDataResponse<SecurityToken> CreateUser(FreeDataRequest<RegisterForm> user);

    public BaseResponse DeleteUser(User user);

    public BaseResponse ChangePassword(User user, String newPassword);

    public BaseResponse ChangeEmail(User user, String newEmail);

    public BaseResponse ChangeUsername(User user, String newUserName);

    public BaseDataResponse<User> GetUserByEmail(String email);

    public BaseDataResponse<User> GetUserByUsername(String email);

}
