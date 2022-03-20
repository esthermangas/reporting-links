package com.reporting.consumer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkEntityEvent extends EntityEvent{

    String id;
    String original;
    String shortened;
    String ownerId;
    Event event;
    String createdAt;

}
