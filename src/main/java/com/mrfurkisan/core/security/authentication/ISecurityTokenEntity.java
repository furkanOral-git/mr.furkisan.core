package com.mrfurkisan.core.security.authentication;

import com.mrfurkisan.core.domain.interfaces.IEntity;

public interface ISecurityTokenEntity extends IEntity{
    
    public String GetCurrentMacAddress();
    public int GetRoleId();
}
