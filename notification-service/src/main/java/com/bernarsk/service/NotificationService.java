package com.bernarsk.service;

import com.bernarsk.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final WebClient.Builder webClientBuilder;

    @KafkaListener(topics = "notificationTopic", groupId = "notificationId")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
        log.info("Received Notification for Order - {}", orderPlacedEvent.getOrderNumber());
        Order order = webClientBuilder.build().get()
                .uri("http://order-service/api/products",
                        uriBuilder -> uriBuilder.queryParam("productId", productIDs).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
