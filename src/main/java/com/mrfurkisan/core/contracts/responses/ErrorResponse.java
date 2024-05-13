package com.mrfurkisan.core.contracts.responses;


import com.mrfurkisan.core.contracts.abstracts.BaseResponse;

public class ErrorResponse extends BaseResponse {
    
    public ErrorResponse( String message) {
        
        super( false, message);
    }
}
