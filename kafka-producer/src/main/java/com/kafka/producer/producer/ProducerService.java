package com.kafka.producer.producer;

import com.kafka.producer.model.News;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<News, News> kafkaTemplate;
    private final Random random = new Random();
    private boolean isProducing = false;

    public Flux<News> startProducing() {
        isProducing = true;
        return Flux.interval(Duration.ofSeconds(2))
                .map(e -> produceNews());
    }

    private News produceNews() {
        String key = String.valueOf(random.nextInt(255));
        String value = "Some news - " + random.nextInt(1000);
        News news = new News("Key: " + key + " Value: " + value);
        kafkaTemplate.send("news", news, news);
        return news;
    }

    public Mono<String> stopProducing() {
        isProducing = false;
        return Mono.just("Producing has stopped");
    }
}
