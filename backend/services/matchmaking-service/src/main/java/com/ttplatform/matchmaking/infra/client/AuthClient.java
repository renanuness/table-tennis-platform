package com.ttplatform.matchmaking.infra.client;

import com.ttplatform.matchmaking.domain.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient(
        name = "auth-service",
        url = "${feign.auth-service.url}",
        fallback = AuthClientFallback.class
)
public interface AuthClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/auth/user/{id}")
    User getById(@PathVariable UUID id);
}
