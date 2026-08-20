package com.ttplatform.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Rotas para Auth Service
                .route("auth-register", r -> r
                        .path("/api/auth/register")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://auth-service"))

                .route("auth-login", r -> r
                        .path("/api/auth/login")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://auth-service"))

                .route("auth-refresh", r -> r
                        .path("/api/auth/refresh")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://auth-service"))

                // Rotas para outros serviços
                .route("profile-service", r -> r
                        .path("/api/profile/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://profile-service"))

                .route("matchmaking-service", r -> r
                        .path("/api/match/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://matchmaking-service"))

                .build();
    }
}