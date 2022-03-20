package com.reporting.consumer;

import com.reporting.consumer.entities.ClickEvent;
import com.reporting.entities.Click;
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
public class ClickConsumer {

    private final ReportingEventsService reportingEventsService;

    @KafkaListener(topics = "Clicks", containerFactory = "kafkaListenerContainerClickFactory")
    public void listenUser(ClickEvent clickEvent) {
        Click click = new Click();
        click.setLinkId(clickEvent.getLinkId());
        LocalDateTime localDateTime = LocalDateTime.parse(clickEvent.getCreatedAt(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        click.setTime(localDateTime.toInstant(ZoneOffset.UTC));
        reportingEventsService.writeEvent(click);
    }
}
