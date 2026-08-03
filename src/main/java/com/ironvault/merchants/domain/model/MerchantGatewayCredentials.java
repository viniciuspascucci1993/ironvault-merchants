package com.ironvault.merchants.domain.model;
import java.time.LocalDateTime;
import java.util.UUID;

public class MerchantGatewayCredentials {

    private UUID id;
    private UUID merchantId;
    private String accessToken;
    private String refreshToken;
    private String mpUserId;
    private LocalDateTime  expiresAt;
    private LocalDateTime  connectedAt;
    private LocalDateTime updatedAt;

    public MerchantGatewayCredentials() { }

    public static MerchantGatewayCredentials create(UUID merchantId, String accessToken,
                                                    String refreshToken, String mpUserId,
                                                    LocalDateTime expiresAt) {

        return new MerchantGatewayCredentials(
                UUID.randomUUID(),
                merchantId,
                accessToken,
                refreshToken,
                mpUserId,
                expiresAt,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public MerchantGatewayCredentials(UUID id, UUID merchantId,
                                      String accessToken, String refreshToken,
                                      String mpUserId, LocalDateTime expiresAt,
                                      LocalDateTime connectedAt, LocalDateTime updatedAt) {
        this.id = id;
        this.merchantId = merchantId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.mpUserId = mpUserId;
        this.expiresAt = expiresAt;
        this.connectedAt = connectedAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(UUID merchantId) {
        this.merchantId = merchantId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getMpUserId() {
        return mpUserId;
    }

    public void setMpUserId(String mpUserId) {
        this.mpUserId = mpUserId;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getConnectedAt() {
        return connectedAt;
    }

    public void setConnectedAt(LocalDateTime connectedAt) {
        this.connectedAt = connectedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
