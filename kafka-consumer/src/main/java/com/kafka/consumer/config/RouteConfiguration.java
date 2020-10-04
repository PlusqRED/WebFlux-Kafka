package com.kafka.consumer.config;

import com.kafka.consumer.handler.NewsHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RouteConfiguration {
    private final NewsHandler newsHandler;


    @Bean
    RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route(RequestPredicates.GET("/news"), newsHandler::getNews);
    }
}
