package com.ironvault.merchants.adapter.out.persistence;

import com.ironvault.merchants.adapter.out.entity.MerchantGatewayCredentialsEntity;
import com.ironvault.merchants.domain.model.MerchantGatewayCredentials;
import com.ironvault.merchants.domain.port.out.MerchantGatewayCredentialsRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class MerchantGatewayCredentialsRepositoryAdapter implements MerchantGatewayCredentialsRepositoryPort {

    private final MerchantGatewayCredentialsJpaRepository jpaRepository;

    public MerchantGatewayCredentialsRepositoryAdapter(MerchantGatewayCredentialsJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public MerchantGatewayCredentials save(MerchantGatewayCredentials credentials) {
        MerchantGatewayCredentialsEntity entity = toEntity(credentials);
        MerchantGatewayCredentialsEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<MerchantGatewayCredentials> findByMerchantId(UUID merchantId) {
        return jpaRepository.findByMerchantId(merchantId)
                .map(this::toDomain);
    }


    private MerchantGatewayCredentialsEntity toEntity(MerchantGatewayCredentials domain) {
        return new MerchantGatewayCredentialsEntity(
                domain.getId(),
                domain.getMerchantId(),
                domain.getAccessToken(),
                domain.getRefreshToken(),
                domain.getMpUserId(),
                domain.getExpiresAt(),
                domain.getConnectedAt(),
                domain.getUpdatedAt()
        );
    }

    private MerchantGatewayCredentials toDomain(MerchantGatewayCredentialsEntity entity) {
        return new MerchantGatewayCredentials(
                entity.getId(),
                entity.getMerchantId(),
                entity.getAccessToken(),
                entity.getRefreshToken(),
                entity.getMpUserId(),
                entity.getExpiresAt(),
                entity.getConnectedAt(),
                entity.getUpdatedAt()
        );
    }
}
