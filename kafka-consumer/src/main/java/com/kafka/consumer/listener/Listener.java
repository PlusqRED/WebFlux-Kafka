package com.kafka.consumer.listener;

import com.kafka.producer.model.News;

public interface Listener {
    void onMessage(News news);
}
