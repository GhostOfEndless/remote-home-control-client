package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService extends TextWebSocketHandler {

    private static final String URL = "ws://localhost:8080/tutorial";
    private final WebSocketClient webSocketClient;
    private WebSocketSession webSocketSession;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationStart() {
        connect();
    }

    @EventListener(ContextClosedEvent.class)
    public void onApplicationClosed() {
        log.info("Disconnecting from server...");
        disconnect();
    }

    private void connect() {
        while (true) {
            try {
                log.info("Connecting to server...");
                webSocketClient.execute(this, new WebSocketHttpHeaders(),
                        URI.create(URL)).get();
                return;
            } catch (Exception e) {
                log.error("Error connecting to WebSocket server: {}", e.getMessage());
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void disconnect() {
        try {
            if (webSocketSession != null && webSocketSession.isOpen()) {
                webSocketSession.close();
            }
        } catch (IOException e) {
            log.error("Error closing session: {}", e.getMessage());
        }
    }

    public void sendMessage(String message) {
        try {
            webSocketSession.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            log.error("Error sending message: {}", e.getMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Connection closed. Session id: {} Status: {}", session.getId(), status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("Received message from server: {}", message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection established with server. Session id: {}", session.getId());
        webSocketSession = session;
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("Transport error: {} on session: {}", exception.getMessage(), session.getId());
    }
}
