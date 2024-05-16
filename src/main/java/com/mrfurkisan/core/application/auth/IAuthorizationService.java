package com.mrfurkisan.core.application.auth;

import com.mrfurkisan.core.application.IApplicationService;
import com.mrfurkisan.core.infrastructure.security.IRoleBuilder;

public interface IAuthorizationService extends IApplicationService {
    public IRoleBuilder GetRoleBuilder();
}
