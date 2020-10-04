package com.kafka.consumer.listener;

import com.kafka.producer.model.News;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@Log4j2
public class NewsListener {

    @Setter
    private Listener newsListener;

    @KafkaListener(topics = "news", groupId = "news-consumer")
    void newsConsumer(News news) {
        log.info("Message has been received: " + news);
        onEvent(news);
    }

    public void onEvent(News news) {
        if (newsListener != null) {
            newsListener.onMessage(news);
        }
    }
}
