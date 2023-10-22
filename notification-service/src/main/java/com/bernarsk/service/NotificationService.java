package com.bernarsk.service;

import com.bernarsk.dto.OrderDTO;
import com.bernarsk.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final WebClient.Builder webClientBuilder;
    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "notificationTopic", groupId = "notificationId")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
        Long orderNumber = orderPlacedEvent.getOrderNumber();
        log.info("Received Notification for Order - {}", orderNumber);

        // call webClient
        Mono<OrderDTO> resultMono = webClientBuilder.build().get()
                .uri("http://order-service/api/order/{orderNumber}",
                        uriBuilder -> uriBuilder.build(orderNumber))
                .retrieve()
                .bodyToMono(OrderDTO.class);
        OrderDTO orderDTO = resultMono.block();
        sendEmailNotification("kazoksbernars@gmail.com", "New Order", orderDTO.toString());
    }

    public void sendEmailNotification(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }
}
