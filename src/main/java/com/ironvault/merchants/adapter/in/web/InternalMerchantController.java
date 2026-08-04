package com.ironvault.merchants.adapter.in.web;

import com.ironvault.merchants.domain.port.out.MerchantGatewayCredentialsRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/internal/merchants")
public class InternalMerchantController {

    @Value("${app.auth.internal-api-key}")
    private String internalApiKey;

    private final MerchantGatewayCredentialsRepositoryPort credentialsRepositoryPort;

    public InternalMerchantController(MerchantGatewayCredentialsRepositoryPort credentialsRepositoryPort) {
        this.credentialsRepositoryPort = credentialsRepositoryPort;
    }

    @GetMapping("/{merchantId}/credentials")
    public ResponseEntity<Map<String, String>> getCredentials(
            @RequestHeader("X-Internal-Key") String internalKey,
            @PathVariable UUID merchantId) {

        if (!internalApiKey.equals(internalKey)) {
            return ResponseEntity.status(401).build();
        }

        return credentialsRepositoryPort.findByMerchantId(merchantId)
                .map(credentials -> ResponseEntity.ok(Map.of(
                        "accessToken", credentials.getAccessToken(),
                        "mpUserId", credentials.getMpUserId() != null ? credentials.getMpUserId() : ""
                )))
                .orElse(ResponseEntity.notFound().build());
    }
}
