package com.mrfurkisan.core.application.auth;

import com.mrfurkisan.core.application.IApplicationService;
import com.mrfurkisan.core.contracts.abstracts.BaseDataResponse;
import com.mrfurkisan.core.contracts.abstracts.BaseResponse;
import com.mrfurkisan.core.security.authentication.SecurityToken;
import com.mrfurkisan.core.security.authentication.SecurityTokenEntity;
import com.mrfurkisan.core.security.authentication.User;

public interface ITokenService extends IApplicationService {

    public BaseDataResponse<SecurityToken> CreateToken(User user);

    public BaseResponse DeleteToken(SecurityTokenEntity tokenEntity);

    public BaseResponse ValidateToken(SecurityToken token);

}
