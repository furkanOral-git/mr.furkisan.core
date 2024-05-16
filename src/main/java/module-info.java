module core {

    exports com.mrfurkisan.core.application.forms;
    exports com.mrfurkisan.core.application.config;
    exports com.mrfurkisan.core.application;
    exports com.mrfurkisan.core.application.repositories;
    exports com.mrfurkisan.core.application.auth;

    exports com.mrfurkisan.core.domain;
    exports com.mrfurkisan.core.domain.functional;
    exports com.mrfurkisan.core.domain.interfaces;

    exports com.mrfurkisan.core.security.authorization;
    exports com.mrfurkisan.core.security.authentication;

    exports com.mrfurkisan.core.infrastructure.security;
    exports com.mrfurkisan.core.infrastructure.persistence;
    exports com.mrfurkisan.core.infrastructure.services;

    exports com.mrfurkisan.core.contracts.abstracts;
    exports com.mrfurkisan.core.contracts.requests;
    exports com.mrfurkisan.core.contracts.responses;

    requires transitive jakarta.persistence;
    requires spring.context;
    requires spring.core;
    requires transitive spring.web;
    requires spring.tx;
    requires lombok;
    requires spring.beans;
    
    
    

}
