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
                        .uri("lb://auth-service"))

                .route("auth-login", r -> r
                        .path("/api/auth/login")
                        .uri("lb://auth-service"))

                .route("auth-refresh", r -> r
                        .path("/api/auth/refresh")
                        .uri("lb://auth-service"))

                // Rotas para outros serviços
                .route("profile-service", r -> r
                        .path("/api/profile/**")
                        .uri("lb://profile-service"))

                .route("matchmaking-service", r -> r
                        .path("/api/match/**")
                        .uri("lb://matchmaking-service"))

                .build();
    }
}