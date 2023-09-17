package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerSpringBootApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerSpringBootApplication.class);
    }

    @Autowired
    private WikimediaChangesProducer wikimediaChangesProducer;

    // This run() is of CommandLineRunner and this execute when application get's started
    @Override
    public void run(String... args) throws Exception {
        wikimediaChangesProducer.sendMessage();
    }
}