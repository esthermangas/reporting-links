package com.reporting.influx;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.InfluxDBClientOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(InfluxDbConfigurationProperties.class)
public class InfluxDBConfig {

    private final InfluxDbConfigurationProperties properties;

    @Bean
    public InfluxDBClient reportingEventsDBClient(){
        InfluxDBClientOptions options = InfluxDBClientOptions.builder()
                .org(properties.getOrganization())
                .url(properties.getUrl())
                .authenticate(properties.getUsername(), properties.getPassword().toCharArray())
                .bucket(properties.getBucket())
                .authenticateToken(properties.getAdminToken().toCharArray())
                .build();
        return InfluxDBClientFactory.create(options);
    }
}
