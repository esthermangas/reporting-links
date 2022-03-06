package com.reporting.entities;


import com.reporting.consumer.entities.Event;

public class Link extends AbstractEntity{

    private String linkId;
    private String original;
    private String shortened;
    private int counter;
    private String ownerId;
    private Event event;

}
