package com.ttplatform.matchmaking.infra.client;

import com.ttplatform.matchmaking.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthClientFallback implements AuthClient {

    @Override
    public User getById(UUID id) {
        // Log do erro
        //log.error("Fallback: Não foi possível buscar usuário {}", id);

        // Retorna objeto vazio ou lança exceção
        throw new ClientUnavailableException("Serviço de auth indisponível");
    }
}