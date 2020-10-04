package com.kafka.producer.config;

import com.kafka.producer.handler.ProducerHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class RouteConfiguration {
    private final ProducerHandler producerHandler;

    @Bean
    RouterFunction<ServerResponse> routes() {
        return route(GET("/start"), producerHandler::startProducing)
                .andRoute(GET("/stop"), producerHandler::stopProducing);
    }
}
