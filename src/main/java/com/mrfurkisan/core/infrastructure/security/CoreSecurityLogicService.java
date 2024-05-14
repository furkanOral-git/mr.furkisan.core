package com.mrfurkisan.core.infrastructure.security;

import com.mrfurkisan.core.security.authorization.AccessDimensionLevel;
import com.mrfurkisan.core.security.authorization.AccessLevel;
import com.mrfurkisan.core.security.authorization.Authority;
import com.mrfurkisan.core.security.authorization.AuthorityDetails;

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

    public static AuthorityDetails CheckAuthorityAnnotations() {

        /*
         * C# taki getFrames() methodu yerine kullanılabilecek yöntem. Controller'dan
         * istek
         * geldiğinde bu kod bloğuna kadar olan adımları(Frame'leri) izleyerek
         * Controller sınıf üzerindeki
         * annotation'ın ayarlarına ulaşarak bir AuthorityDetails nesnesi oluşturacak.
         */
        var stackTraces = Thread.currentThread().getStackTrace();
        
        Authority classLevelAnnotation = null;
        Authority methodlevelAnnotation = null;

        for (StackTraceElement element : stackTraces) {

            var className = element.getClassName();

            /*
             * Bu bloğa kadar olan sınıfların isimlerini alıp, bunları bütün harfleri küçük
             * olacak şekile getirip, içerisinde controller kelimesi var mı kontrol
             * ediyorum.
             */

            if (!className.toLowerCase().contains("controller")) {
                continue;
            }
            var methodName = element.getMethodName();
            var clss = element.getClass();
            // class seviyesindeki annotation kontrolü için
            classLevelAnnotation = clss.getAnnotation(Authority.class);
            methodlevelAnnotation = null;
            for (var method : clss.getDeclaredMethods()) {

                if (method.getName() == methodName) {
                    methodlevelAnnotation = method.getAnnotation(Authority.class);
                    break;
                }
            }
            break;
        }
        return new AuthorityDetails(classLevelAnnotation, methodlevelAnnotation);

    }
}
