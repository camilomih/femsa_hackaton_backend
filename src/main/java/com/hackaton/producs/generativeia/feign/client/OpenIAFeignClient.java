package com.hackaton.producs.generativeia.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hackaton.producs.generativeia.feign.OpenIACLientConfig;
import com.hackaton.producs.generativeia.feign.dtos.ResponseOpen;
import com.hackaton.producs.generativeia.feign.dtos.SendMessage;

@Component
@FeignClient(name = "openIA", url = "${openia.url}", configuration = { OpenIACLientConfig.class })
public interface OpenIAFeignClient {

    @PostMapping(value = "/v1/chat/completions")
    public ResponseOpen askChatGPT(@RequestBody SendMessage message);

}
