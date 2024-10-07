package org.example.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebSocketClientService {

    private final WebSocketStompClient stompClient;
    private StompSession stompSession;

    @PostConstruct
    public void connect() {
        try {
            var url = "ws://localhost:8080/ws";
            stompSession = stompClient.connectAsync(url, new StompSessionHandlerAdapter() {}).get();
            stompSession.subscribe("/topic/greetings", new StompFrameHandler() {
                @Override
                public @NotNull Type getPayloadType(@NotNull StompHeaders headers) {
                    return String.class;
                }

                @Override
                public void handleFrame(@NotNull StompHeaders headers, Object payload) {
                    log.info("Received message: {}", payload);
                }
            });
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error in websocket client: {}", e.getMessage());
        }
    }

    public void sendMessage(String message) {
        if (stompSession != null && stompSession.isConnected()) {
            stompSession.send("/app/hello", message);
            log.info("Sent message: {}", message);
        } else {
            log.error("Not connected to WebSocket server");
        }
    }
}
