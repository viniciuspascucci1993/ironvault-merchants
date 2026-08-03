package com.ironvault.merchants.domain.port.out;

import com.ironvault.merchants.domain.model.MerchantGatewayCredentials;

public interface MercadoPagoOAuthPort {

    MerchantGatewayCredentials exchangeCodeForToken(String code, String redirectUri);
}
