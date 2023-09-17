package com.example;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    // Here we can also declare partition
    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("wikimedia_recentchange")
                .build();
    }
}
