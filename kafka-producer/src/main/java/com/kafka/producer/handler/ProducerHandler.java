package com.kafka.producer.handler;

import com.kafka.producer.model.News;
import com.kafka.producer.producer.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProducerHandler {
    private final ProducerService producerService;

    public Mono<ServerResponse> startProducing(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(producerService.startProducing(), News.class);
    }

    public Mono<ServerResponse> stopProducing(ServerRequest serverRequest) {
        return ServerResponse.ok().body(producerService.stopProducing(), String.class);
    }
}
