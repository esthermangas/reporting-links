package com.reporting.consumer;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfiguration {
    @Value(value = "localhost:9092")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic userTopic() {
        return new NewTopic("UserEntityEvent", 1, (short) 1);
    }
    @Bean
    public NewTopic linkTopic() {
        return new NewTopic("LinkEntityEvent", 1, (short) 1);
    }
    @Bean
    public NewTopic clicks() {
        return new NewTopic("Clicks", 1, (short) 1);
    }
}
