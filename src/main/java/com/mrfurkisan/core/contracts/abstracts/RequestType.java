package com.mrfurkisan.core.contracts.abstracts;

import com.mrfurkisan.core.domain.interfaces.IAggregateRootItem;

public enum RequestType implements IAggregateRootItem{
    GET,
    POST,
    PUT,
    DELETE
}
