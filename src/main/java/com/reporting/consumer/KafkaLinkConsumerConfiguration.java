package com.reporting.consumer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.reporting.consumer.entities.GenericEvent;
import com.reporting.consumer.entities.LinkEntityEvent;
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
public class KafkaLinkConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, LinkEntityEvent> consumerLinkFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "reporting");


        JsonDeserializer<LinkEntityEvent> jsonDeserializer = new JsonDeserializer<>(new TypeReference<>() {});
        jsonDeserializer.setUseTypeHeaders(false);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LinkEntityEvent>
    kafkaListenerContainerLinkFactory() {
        ConcurrentKafkaListenerContainerFactory<String, LinkEntityEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerLinkFactory());
        return factory;
    }
}
