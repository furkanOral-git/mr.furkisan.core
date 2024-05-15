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
    private int __userId;

    public SecurityTokenEntity(String uniqueId, String macAdress, int roleId, int userId) {
        this.__uniqueId = uniqueId;
        this.__macAddress = macAdress;
        this.__roleId = roleId;
        this.__userId = userId;
    }

    public String GetCurrentMacAddress() {
        return this.__macAddress;
    }
    public int GetUserId(){
        return this.__userId;
    }
    public int GetRoleId() {
        return this.__roleId;
    }

    @Override
    public String GetId() {
        return this.__uniqueId;
    }

}
