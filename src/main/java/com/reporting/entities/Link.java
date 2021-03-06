package com.reporting.entities;


import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.reporting.consumer.entities.EntityEvent;
import com.reporting.consumer.entities.Event;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;


@Getter
@Setter
@Measurement(name = "Links")
public class Link implements EventEntity {

    @Column()
    private String linkId;
    @Column()
    private String original;
    @Column()
    private String shortened;
    @Column()
    private String ownerId;
    @Column(tag = true)
    private Event event;
    @Column(timestamp = true)
    private Instant time;

}
