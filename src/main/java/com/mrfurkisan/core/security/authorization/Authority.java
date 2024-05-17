package com.mrfurkisan.core.security.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {

    AccessDimensionLevel AccessDimensionLevel() default AccessDimensionLevel.Authenticated;

    DomainAccessLevel DomainAccess() default DomainAccessLevel.Public;

    DomainName[] OpenTo() default DomainName.NORMAL;

}
