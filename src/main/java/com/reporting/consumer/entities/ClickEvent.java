package com.reporting.consumer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClickEvent extends EntityEvent{
    String linkId;
    String createdAt;

}
