package com.ironvault.merchants.adapter.out.mercadopago;

import com.fasterxml.jackson.databind.JsonNode;
import com.ironvault.merchants.domain.model.MerchantGatewayCredentials;
import com.ironvault.merchants.domain.port.out.MercadoPagoOAuthPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class MercadoPagoOAuthAdapter implements MercadoPagoOAuthPort {

    private static final String TOKEN_URL = "https://api.mercadopago.com/oauth/token";

    private final RestTemplate restTemplate;

    @Value("${mercadopago.client-id}")
    private String clientId;

    @Value("${mercadopago.client-secret}")
    private String clientSecret;

    public MercadoPagoOAuthAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public MerchantGatewayCredentials exchangeCodeForToken(String code, String redirectUri) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("client_id", clientId);
        body.put("client_secret", clientSecret);
        body.put("grant_type", "authorization_code");
        body.put("code", code);
        body.put("redirect_uri", redirectUri);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        JsonNode response = restTemplate.postForObject(TOKEN_URL, request, JsonNode.class);

        if (response == null) {
            throw new IllegalStateException("Resposta vazia do Mercado Pago ao trocar o code pelo token");
        }

        String accessToken = response.get("access_token").asText();
        String refreshToken = response.has("refresh_token") ? response.get("refresh_token").asText() : null;
        String mpUserId = response.has("user_id") ? response.get("user_id").asText() : null;
        long expiresInSeconds = response.has("expires_in") ? response.get("expires_in").asLong() : 0;

        LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(expiresInSeconds);

        return MerchantGatewayCredentials.create(
                UUID.randomUUID(),
                accessToken,
                refreshToken,
                mpUserId,
                expiresAt
        );
    }
}
