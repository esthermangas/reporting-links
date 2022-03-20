package com.reporting.consumer.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GenericEvent <E extends EntityEvent> {
    public E event;
}
