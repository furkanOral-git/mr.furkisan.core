package com.mrfurkisan.core.application.auth;

import com.mrfurkisan.core.application.IApplicationService;
import com.mrfurkisan.core.security.authentication.ISecurityToken;
import com.mrfurkisan.core.security.authentication.SecurityToken;
import com.mrfurkisan.core.security.authentication.SecurityTokenEntity;
import com.mrfurkisan.core.security.authentication.User;

public interface ITokenService extends IApplicationService {

    public SecurityToken CreateToken(User user);

    public Boolean DeleteToken(SecurityTokenEntity tokenEntity);

    public SecurityTokenEntity GetTokenById(ISecurityToken token);
}
