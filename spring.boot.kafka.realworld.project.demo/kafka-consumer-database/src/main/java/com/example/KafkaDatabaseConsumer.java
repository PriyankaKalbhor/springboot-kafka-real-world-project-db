package com.example;

import com.example.Entity.WikimediaData;
import com.example.Repository.WikimediaDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaDatabaseConsumer {

    private final String topic = "wikimedia_recentchange";

    private final WikimediaDataRepository wikimediaDataRepository;

    public KafkaDatabaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }


    @KafkaListener(topics = topic, groupId = "myGroup")
    public void consume(String eventMessage){
        log.info("Event Message received by consumer : {}", eventMessage);

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        log.info("wikimediaData : {} ",wikimediaData);
        wikimediaDataRepository.save(wikimediaData);
    }

}
