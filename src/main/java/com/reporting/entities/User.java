package com.reporting.entities;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.reporting.consumer.entities.Event;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
@Measurement(name = "Users")
public class User implements EventEntity{

  @Column()
  String userId;
  @Column()
  boolean enabled;
  @Column()
  String username;
  @Column()
  String firstName;
  @Column()
  String lastName;
  @Column()
  Long maxRequest;
  @Column()
  Long windowTimeMS;
  @Column(tag = true)
  Event event;
  @Column(timestamp = true)
  private Instant time;
}
