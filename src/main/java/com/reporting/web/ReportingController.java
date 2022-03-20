package com.reporting.web;

import com.reporting.consumer.entities.EntityEvent;
import com.reporting.consumer.entities.Event;
import com.reporting.entities.EventEntity;
import com.reporting.entities.User;
import com.reporting.influx.ReportingEventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController(value = "/")
@RequiredArgsConstructor
public class ReportingController {

    private final ReportingEventsService reportingEventsService;

    @GetMapping
    public List<?> getEvents(
            @RequestParam(name = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestParam(name = "event", required = false)Event event,
            @RequestParam(name = "entity", required = true) String className
            ) throws ClassNotFoundException {

        return reportingEventsService.readEvents(Class.forName("com.reporting.entities."+className), from , to, event);
    }
}
