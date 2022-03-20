package com.reporting.consumer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityEvent extends EntityEvent{

    String id;
    boolean enabled;
    String username;
    String firstName;
    String lastName;
    int maxRequest;
    int windowTimeMS;
    Event event;
    String createdAt;
}
