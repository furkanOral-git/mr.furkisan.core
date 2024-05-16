package com.mrfurkisan.core.infrastructure.security;

import com.mrfurkisan.core.contracts.abstracts.BaseResponse;
import com.mrfurkisan.core.contracts.abstracts.RequestTypesEnum;
import com.mrfurkisan.core.contracts.responses.ErrorResponse;
import com.mrfurkisan.core.contracts.responses.SuccessResponse;
import com.mrfurkisan.core.security.authorization.AccessDimensionLevel;
import com.mrfurkisan.core.security.authorization.AccessLevel;
import com.mrfurkisan.core.security.authorization.Authority;
import com.mrfurkisan.core.security.authorization.AuthorityDetails;
import com.mrfurkisan.core.security.authorization.DomainAccessLevel;
import com.mrfurkisan.core.security.authorization.Role;

final class CoreSecurityAuthorizationLogic {

    private static Boolean IsItEnoughForAccess(AccessLevel levelForRole, AccessDimensionLevel dimensionLevel) {

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

    private static BaseResponse IsValidForAuthority(Role role, Authority level, RequestTypesEnum requestType) {

        if (!role.CanYouDo(requestType)) {
            return new ErrorResponse("Forbidden action!");
        }

        if (!CoreSecurityAuthorizationLogic.IsItEnoughForAccess(role.getLevel(), level.AccessDimensionLevel())) {
            return new ErrorResponse("Not Authorized");
        }

        if (!CoreSecurityAuthorizationLogic.CheckDomainNameAndDomainAccess(role, level)) {
            return new ErrorResponse("Yetki siniri ihlali!");
        }

        return new SuccessResponse("Başarili");
    }

    private static Boolean CheckDomainNameAndDomainAccess(Role role, Authority level) {

        var access = level.DomainAccess();
        Boolean itWllPass = false;

        if (access == DomainAccessLevel.DomainNameOnly) {

            for (var domain : level.DomainName()) {

                if (role.IsExistDomain(domain)) {
                    itWllPass = true;
                    break;
                }
                ;
            }
            return itWllPass;
        }
        return true;
    }

    private static AuthorityDetails CheckAuthorityAnnotations() {

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
    
    public static BaseResponse ValidateAuthority(Role role, RequestTypesEnum req) {

        AuthorityDetails details = CoreSecurityAuthorizationLogic.CheckAuthorityAnnotations();
        if (details.classLevel() == null && details.methodLevel() == null) {
            return new ErrorResponse(
                    "Internal Error : Check the authority configuration of controllers, that error occurs on missing annotation declaring!");
        }

        /*
         * Class level kontrolü
         * Kural : MethodLevel > ClassLevel
         * Yani öncelikli olarak method level kurallarından geçmiş olması gerekiyor
         * .
         */

        Authority classLevel = details.classLevel();
        Authority methodLevel = details.methodLevel();
        if (methodLevel == null) {

            return CoreSecurityAuthorizationLogic.IsValidForAuthority(role, classLevel, req);
        }
        if (classLevel == null) {

            return CoreSecurityAuthorizationLogic.IsValidForAuthority(role, methodLevel, req);
        }
        if (methodLevel != null && classLevel != null) {

            return CoreSecurityAuthorizationLogic.IsValidForAuthority(role, methodLevel, req);
        }
        return new ErrorResponse("Not Authorized!");
    }
}
