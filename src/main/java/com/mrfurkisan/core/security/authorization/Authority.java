package com.mrfurkisan.core.security.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {

    AccessDimensionLevel SetAccessDimensionLevel() default AccessDimensionLevel.Authenticated;

    DomainAccessLevel SetHorizontalDomainAccess() default DomainAccessLevel.Public;

    String SetDomainName();

}
