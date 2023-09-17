package com.example;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WikimediaChangesProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {

        String topic="wikimedia_recentchange";

        // To read real time Stream data from wikimedia, we use event source
        // We use dependency OkHttp EventSource 2.5.0 version

        // This handler will trigger whenever there is event in wikimedia and EventSource will be connect to the Wikimedia source then it go to handler and then after we send that data to perticular topic
        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate,topic);

        // This below url provides real-time wikimedia stream data
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));

        // Create Eventsource object
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);

    }

}
