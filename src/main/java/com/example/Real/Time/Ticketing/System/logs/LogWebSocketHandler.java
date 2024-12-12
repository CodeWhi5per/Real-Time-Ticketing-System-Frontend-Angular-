package com.example.Real.Time.Ticketing.System.logs;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class LogWebSocketHandler extends TextWebSocketHandler {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        executorService.submit(() -> {
            try {
                while (session.isOpen()) {
                    String log = WebLogAppender.getLogQueue().poll();
                    if (log != null) {
                        session.sendMessage(new TextMessage(log));
                    }
                    Thread.sleep(100); // Avoid tight loop
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
