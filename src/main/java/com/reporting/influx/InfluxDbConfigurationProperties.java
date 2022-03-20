package com.reporting.influx;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "reporting.influx")
@Getter
@Setter
public class InfluxDbConfigurationProperties {

    private String username = "root";
    private String password = "root1234";
    private String organization = "reporting";
    private String bucket = "reportingEvents";
    private String adminToken = "root";
    private String url= "http://localhost:8086/";
}
