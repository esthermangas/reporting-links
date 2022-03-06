package com.reporting.consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserConsumer {

    @KafkaListener(topics = "UserEventEntity", groupId = "user-reporting")
    public void listenUser(String message) {
        System.out.println("Received Message in group user-reporting: " + message);
    }
}
