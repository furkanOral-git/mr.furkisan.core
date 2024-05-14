package com.mrfurkisan.core.security.authentication;

import com.mrfurkisan.core.domain.BaseEntity;
import jakarta.persistence.Id;

//@Entity: IEntity üzerinde tanımladığımız için ve @inheritance ile miras yolunu da açtığımız için gerek yok.
public final class User extends BaseEntity<Number> {

    @Id
    private int __userId;
    private int __roleId;
    private String __defaultMacAddress;
    private String __email;
    private String __password;
    private String __userName;

    User(int userId, int roleId, String defaultMacAdress, String email, String password, String userName) {
        this.__userId = userId;
        this.__roleId = roleId;
        this.__defaultMacAddress = defaultMacAdress;
        this.__email = email;
        this.__password = password;
        this.__userName = userName;
    }

    public int GetRoleId() {
        return this.__roleId;
    }

    public Number GetId() {
        return this.__userId;
    }

    public String GetDefaultMacAddress() {
        return this.__defaultMacAddress;
    }

    public String GetEmail() {
        return this.__email;
    }

    public String GetPassword() {
        return this.__password;
    }

    public String GetUserName() {
        return this.__userName;
    }
}
