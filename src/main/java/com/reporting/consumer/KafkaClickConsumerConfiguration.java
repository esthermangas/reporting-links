package com.reporting.consumer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.reporting.consumer.entities.LinkEntityEvent;
import com.reporting.entities.Click;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaClickConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, Click> consumerClickFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "reporting");


        JsonDeserializer<Click> jsonDeserializer = new JsonDeserializer<>(new TypeReference<>() {});
        jsonDeserializer.setUseTypeHeaders(false);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Click>
    kafkaListenerContainerClickFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Click> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerClickFactory());
        return factory;
    }
}
