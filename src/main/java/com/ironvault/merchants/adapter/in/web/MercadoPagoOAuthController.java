package com.ironvault.merchants.adapter.in.web;

import com.ironvault.merchants.domain.model.MerchantGatewayCredentials;
import com.ironvault.merchants.domain.port.in.ConnectMerchantMercadoPagoAccountUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/merchants/mercadopago")
public class MercadoPagoOAuthController {

    private final ConnectMerchantMercadoPagoAccountUseCase connectMerchantMercadoPagoAccountUseCase;

    @Value("${mercadopago.client-id}")
    private String clientId;

    @Value("${mercadopago.redirect-uri}")
    private String redirectUri;

    public MercadoPagoOAuthController(ConnectMerchantMercadoPagoAccountUseCase connectMerchantMercadoPagoAccountUseCase) {
        this.connectMerchantMercadoPagoAccountUseCase = connectMerchantMercadoPagoAccountUseCase;
    }

    @GetMapping("/authorize-url/{merchantId}")
    public ResponseEntity<Map<String, String>> getAuthorizationUrl(@PathVariable("merchantId") UUID merchantId) {

        String url = "https://auth.mercadopago.com.br/authorization" +
                "?client_id=" + clientId +
                "&response_type=code" +
                "&platform_id=mp" +
                "&state=" + merchantId +
                "&redirect_uri=" + redirectUri;

        return ResponseEntity.ok(Map.of("authorizationUrl", url));
    }

    @GetMapping("/callback")
    public ResponseEntity<Map<String, Object>> handleCallback(
            @RequestParam("code") String code,
            @RequestParam("state") String state) {

        UUID merchantId = UUID.fromString(state);

        MerchantGatewayCredentials credentials =
                connectMerchantMercadoPagoAccountUseCase.connect(merchantId, code, redirectUri);

        return ResponseEntity.ok(Map.of(
                "connected", true,
                "merchantId", credentials.getMerchantId(),
                "mpUserId", credentials.getMpUserId()
        ));
    }
}
