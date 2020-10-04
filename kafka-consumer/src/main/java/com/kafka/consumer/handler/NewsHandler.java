package com.kafka.consumer.handler;

import com.kafka.consumer.listener.NewsListener;
import com.kafka.producer.model.News;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class NewsHandler {
    private final NewsListener newsListener;

    public Mono<ServerResponse> getNews(ServerRequest serverRequest) {
        return ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Flux.create(newsFluxSink ->
                        newsListener.setNewsListener(newsFluxSink::next)
                ), News.class);
    }
}
