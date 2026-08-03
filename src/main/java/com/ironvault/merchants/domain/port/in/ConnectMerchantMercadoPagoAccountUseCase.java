package com.ironvault.merchants.domain.port.in;

import com.ironvault.merchants.domain.model.MerchantGatewayCredentials;

import java.util.UUID;

public interface ConnectMerchantMercadoPagoAccountUseCase {

    MerchantGatewayCredentials connect(UUID merchantId, String code, String redirectURI);
}
