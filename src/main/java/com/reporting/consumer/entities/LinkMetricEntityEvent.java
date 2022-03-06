package com.reporting.consumer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkMetricEntityEvent implements EntityEvent{
    String id;
    String date;
    long count;
    String linkId;


}
