package com.reporting.consumer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkEntityEvent implements EntityEvent{

    String id;
    String original;
    String shortened;
    int counter;
    String ownerId;
    Event event;

}
