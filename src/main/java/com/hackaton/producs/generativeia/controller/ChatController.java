package com.hackaton.producs.generativeia.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.producs.generativeia.feign.client.OpenIAFeignClient;
import com.hackaton.producs.generativeia.feign.dtos.Message;
import com.hackaton.producs.generativeia.feign.dtos.ResponseOpen;
import com.hackaton.producs.generativeia.feign.dtos.SendMessage;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private OpenIAFeignClient openIAFeignClient;

    public ChatController(OpenIAFeignClient openIAFeignClient) {
        this.openIAFeignClient = openIAFeignClient;
    }

    @PostMapping(value = "/test")
    public ResponseEntity<ResponseOpen> test() throws Exception {
        try {
            Message message = Message.builder().content("Prueba responde con un hola mundo!").role("user").build();
        
            SendMessage requestChat = SendMessage.builder().model(SendMessage.GPT_TURBO).messages(List.of(message))
                    .temperature(0.7).build();
            ResponseOpen responseOpen = this.openIAFeignClient.askChatGPT(requestChat);

            return ResponseEntity.ok(responseOpen);
        } catch (Exception e) {
            throw new Exception("Error al consumir el api", e);
        }
    }
}
