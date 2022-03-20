package com.reporting.consumer;
import com.reporting.consumer.entities.UserEntityEvent;
import com.reporting.influx.ReportingEventsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.reporting.entities.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserConsumer {

    private final ReportingEventsService reportingEventsService;

    @KafkaListener(topics = "UserEntityEvent", containerFactory = "kafkaListenerContainerUserFactory")
    public void listenUser(UserEntityEvent genericEvent) {
        User user = new User();
        user.setUserId(genericEvent.getId());
        user.setEnabled(genericEvent.isEnabled());
        user.setEvent(genericEvent.getEvent());
        user.setUsername(genericEvent.getUsername());
        user.setFirstName(genericEvent.getFirstName());
        user.setLastName(genericEvent.getLastName());
        user.setMaxRequest((long) genericEvent.getMaxRequest());
        user.setWindowTimeMS((long) genericEvent.getWindowTimeMS());
        LocalDateTime localDateTime = LocalDateTime.parse(genericEvent.getCreatedAt(),DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        user.setTime(localDateTime.toInstant(ZoneOffset.UTC));
        reportingEventsService.writeEvent(user);
    }
}
