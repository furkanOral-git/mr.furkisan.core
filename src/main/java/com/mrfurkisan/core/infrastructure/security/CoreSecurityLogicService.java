package com.mrfurkisan.core.infrastructure.security;

import com.mrfurkisan.core.security.authorization.AccessDimensionLevel;
import com.mrfurkisan.core.security.authorization.AccessLevel;

final class CoreSecurityLogicService {

    public static Boolean IsItEnoughForAccess(AccessLevel levelForRole, AccessDimensionLevel dimensionLevel) {

        short dLevel = 0;
        short rLevel = 0;
        boolean isItOnlyEnum = false;
        // Büyüktür küçüktür kıyaslaması yapmak için sayılarla rol seviyesini ve katman
        // seviyesini belirliyorum.
        switch (levelForRole) {
            case Authenticated:
                rLevel = 1;
                break;
            case Authorized:
                rLevel = 2;
                break;
            case Intermediate:
                rLevel = 3;
                break;
            case Admin:
                rLevel = 4;
                break;
        }
        switch (dimensionLevel) {

            case Authenticated:
                dLevel = 1;
            case AuthenticatedOnly:
                isItOnlyEnum = true;
                break;
            case Authorized:
                dLevel = 2;
            case AuthorizedOnly:
                isItOnlyEnum = true;
                break;
            case Intermediate:
                dLevel = 3;
                break;
            case Admin:
                dLevel = 4;
                break;
        }

        Boolean result = false;

        // Burada da karşılaştırmaları yapıyorum.
        if (rLevel < dLevel) {
            return result;
        }
        if (rLevel == dLevel) {
            result = true;
        }
        if (rLevel > dLevel && isItOnlyEnum) {
            return result;
        }
        if (rLevel > dLevel) {
            result = true;
        }
        return result;

    }
}
