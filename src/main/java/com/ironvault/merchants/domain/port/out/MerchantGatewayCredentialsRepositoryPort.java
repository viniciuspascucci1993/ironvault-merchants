package com.ironvault.merchants.domain.port.out;

import com.ironvault.merchants.domain.model.MerchantGatewayCredentials;

import java.util.Optional;
import java.util.UUID;

public interface MerchantGatewayCredentialsRepositoryPort {

    MerchantGatewayCredentials save(MerchantGatewayCredentials credentials);
    Optional<MerchantGatewayCredentials> findByMerchantId(UUID merchantId);
}
