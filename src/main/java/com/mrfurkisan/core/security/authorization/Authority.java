package com.mrfurkisan.core.security.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {

    AccessDimensionLevel SetAccessDimensionLevel() default AccessDimensionLevel.Authenticated;

    DomainAccessLevel SetDomainAccess() default DomainAccessLevel.Public;

    DomainName[] SetDomainName() default DomainName.USER;

}
