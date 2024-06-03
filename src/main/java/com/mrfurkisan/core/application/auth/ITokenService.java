package com.mrfurkisan.core.application.auth;

import com.mrfurkisan.core.application.IApplicationService;
import com.mrfurkisan.core.security.authentication.SecurityToken;
import com.mrfurkisan.core.security.authentication.SecurityTokenEntity;

public interface ITokenService extends IApplicationService {

    public SecurityToken CreateToken(int userId, String roleId, String currentMacAddress);

    public void DeleteToken(SecurityTokenEntity tokenEntity);

    public void DeleteByUserId(int id);

    public SecurityTokenEntity GetEntityByUserId(int id);

    public SecurityTokenEntity GetEntityByTokenId(String token);

}
