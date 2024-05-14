package com.mrfurkisan.core.security.authentication;

import com.mrfurkisan.core.domain.BaseEntity;

import jakarta.persistence.Id;

// Veritabanında tutulan nesne. Hiçbir şekilde Client tarafına gitmeycek. Oturum
// sonlandırıldığında
// Veritabanından silinecek.
public class SecurityTokenEntity extends BaseEntity<String> implements ISecurityTokenEntity {
    
    @Id
    private String __uniqueId;
    private String __macAddress;
    private int __roleId;

    SecurityTokenEntity(String uniqueId, String macAdress, int roleId) {
        this.__uniqueId = uniqueId;
        this.__macAddress = macAdress;
        this.__roleId = roleId;
    }

    public String GetCurrentMacAddress() {
        return this.__macAddress;
    }

    public int GetRoleId() {
        return this.__roleId;
    }

    @Override
    public String GetId() {
        return this.__uniqueId;
    }

}
