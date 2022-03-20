package com.reporting.influx;

import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.query.dsl.Flux;
import com.influxdb.query.dsl.functions.restriction.Restrictions;
import com.reporting.consumer.entities.EntityEvent;
import com.reporting.consumer.entities.Event;
import com.reporting.entities.EventEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportingEventsService {

    private final InfluxDBClient client;
    private final InfluxDbConfigurationProperties configurationProperties;


    public void writeEvent(EventEntity eventEntity){

        client.getWriteApiBlocking().writeMeasurement(WritePrecision.NS, eventEntity);

        log.info("Event written to Influx!");
    }

    public List<?> readEvents(Class<?>  returnClass, LocalDateTime from, LocalDateTime to, Event event){

        if(from == null) {
            from = LocalDateTime.parse("1970-01-01T00:00:00", DateTimeFormatter.ISO_DATE_TIME);
        }

        if(to == null) {
            to = LocalDateTime.now();
        }

        Restrictions restrictions = Restrictions.measurement()
                .equal(Arrays.stream(returnClass.getAnnotationsByType(Measurement.class)).findFirst().map(Measurement::name).orElseThrow(RuntimeException::new));
        if(event != null){
            restrictions = Restrictions.and(restrictions, Restrictions.tag("event").equal(event.toString()));
        }
      Flux flux = Flux
              .from(configurationProperties.getBucket())
              .range(from.toInstant(ZoneOffset.UTC), to.toInstant(ZoneOffset.UTC))
              .pivot(List.of("_time"), List.of("_field"), "_value")
              .filter(restrictions);
      return client.getQueryApi().query(flux.toString(), returnClass);
    };

}
