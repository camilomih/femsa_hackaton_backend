package com.hackaton.producs.generativeia.feign;

import org.springframework.http.HttpHeaders;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class OpenIAClientRequestInteceptor implements RequestInterceptor{

    private String openiaKey;

    public OpenIAClientRequestInteceptor(String openiaKey){
        this.openiaKey = openiaKey;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(HttpHeaders.AUTHORIZATION, "Bearer " + openiaKey);
    }

}
