package com.mvp.gateway.config;

import com.mvp.gateway.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r->r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://auth-service"))
                .route("post", r -> r.path("/post/**", "/category/**").filters(f -> f.filter(filter)).uri("lb://post-service"))
                .build();
    }

}
