package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.WebSocketClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageRestController {

    private final WebSocketClientService webSocketClientService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody String message) {
        webSocketClientService.sendMessage(message);
    }
}
