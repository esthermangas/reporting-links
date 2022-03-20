package com.reporting.consumer;

import com.reporting.consumer.entities.LinkEntityEvent;
import com.reporting.entities.Link;
import com.reporting.influx.ReportingEventsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


@Service
@Slf4j
@RequiredArgsConstructor
public class LinkConsumer {

    private final ReportingEventsService reportingEventsService;
    @KafkaListener(topics = "LinkEntityEvent", containerFactory = "kafkaListenerContainerLinkFactory")
    public void listenUser(LinkEntityEvent genericEvent) {
        Link link = new Link();
        link.setLinkId(genericEvent.getId());
        link.setEvent(genericEvent.getEvent());
        link.setOriginal(genericEvent.getOriginal());
        link.setShortened(genericEvent.getShortened());
        link.setOwnerId(genericEvent.getOwnerId());
        LocalDateTime localDateTime = LocalDateTime.parse(genericEvent.getCreatedAt(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        link.setTime(localDateTime.toInstant(ZoneOffset.UTC));

        reportingEventsService.writeEvent(link);
    }
}
