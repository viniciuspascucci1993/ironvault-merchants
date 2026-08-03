package com.ironvault.merchants.adapter.out.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class AuthClient {

    private final RestTemplate restTemplate;

    @Value("${app.auth.url}")
    private String authUrl;

    @Value("${app.auth.internal-api-key}")
    private String internalApiKey;

    public AuthClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void updateUserMerchantId(UUID userId, UUID merchantId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-Internal-Key", internalApiKey);

            Map<String, String> body = Map.of("merchantId", merchantId.toString());

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

            restTemplate.exchange(
                    authUrl + "/api/internal/users/" + userId + "/merchant",
                    org.springframework.http.HttpMethod.PATCH,
                    request,
                    Void.class
            );

            log.info("Merchant id sincronizado no auth. userId={} merchantId={}", userId, merchantId);

        } catch (Exception ex) {
            log.error("Falha ao sincronizar merchantId no auth. userId={} merchantId={} reason={}",
                    userId, merchantId, ex.getMessage());
        }
    }
}
