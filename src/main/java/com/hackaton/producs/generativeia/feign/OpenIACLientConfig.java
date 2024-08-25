package com.hackaton.producs.generativeia.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class OpenIACLientConfig {

    @Value("${openia.key}")
    private String openiaKey;

    @Bean
    public RequestInterceptor getFacturapiInterceptor() {
        return new OpenIAClientRequestInteceptor(openiaKey);
    }
}
