package com.ironvault.merchants.application;

import com.ironvault.merchants.domain.model.MerchantGatewayCredentials;
import com.ironvault.merchants.domain.port.in.ConnectMerchantMercadoPagoAccountUseCase;
import com.ironvault.merchants.domain.port.out.MercadoPagoOAuthPort;
import com.ironvault.merchants.domain.port.out.MerchantGatewayCredentialsRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConnectMerchantMercadoPagoAccountService implements ConnectMerchantMercadoPagoAccountUseCase {

    private final MercadoPagoOAuthPort mercadoPagoOAuthPort;
    private final MerchantGatewayCredentialsRepositoryPort repositoryPort;

    public ConnectMerchantMercadoPagoAccountService(MercadoPagoOAuthPort mercadoPagoOAuthPort,
            MerchantGatewayCredentialsRepositoryPort repositoryPort) {
        this.mercadoPagoOAuthPort = mercadoPagoOAuthPort;
        this.repositoryPort = repositoryPort;
    }

    @Override
    public MerchantGatewayCredentials connect(UUID merchantId, String code, String redirectURI) {

        MerchantGatewayCredentials credentials = mercadoPagoOAuthPort.exchangeCodeForToken(code, redirectURI);
        credentials.setMerchantId(merchantId);
        return repositoryPort.save(credentials);
    }
}
