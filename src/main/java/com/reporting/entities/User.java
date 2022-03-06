package com.reporting.entities;

import com.reporting.consumer.entities.Event;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "users")
@Entity
@Getter
@Setter
public class User extends AbstractEntity{

  @Id @GeneratedValue
  private Long id;
  @Column(name="user_id")
  String userId;
  @Column(name="enabled")
  boolean enabled;
  @Column(name="username")
  String username;
  @Column(name="firstname")
  String firstName;
  @Column(name="lastname")
  String lastName;
  @Column(name="max_request")
  int maxRequest;
  @Column(name="window_time_ms")
  int windowTimeMS;
  @Column(name="event")
  Event event;
}
