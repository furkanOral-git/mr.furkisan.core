package com.mrfurkisan.core.infrastructure.persistence.concrete;

import com.mrfurkisan.core.infrastructure.persistence.BaseInMemoryRepository;
import com.mrfurkisan.core.security.authorization.Role;

public class RoleInMemoryRepository extends BaseInMemoryRepository<Role, Number>{
    // Verileri bellekte tuttuğu için singleton patterni uyguladım.
    private static RoleInMemoryRepository __instance;

    private RoleInMemoryRepository() {
        super();
    }

    public static RoleInMemoryRepository GetRepo() {

        if (__instance == null) {
            __instance = new RoleInMemoryRepository();
        }
        return __instance;
    }
}
