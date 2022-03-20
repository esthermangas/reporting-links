package com.reporting.entities;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.reporting.consumer.entities.Event;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jmx.export.annotation.ManagedNotification;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Measurement(name = "Clicks")
public class Click implements EventEntity {

    @Column()
    private String linkId;
    @Column(tag = true)
    private Event event = Event.CREATE;
    @Column(timestamp = true)
    private Instant time;
}
