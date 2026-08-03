package com.ironvault.merchants.adapter.out.persistence;

import com.ironvault.merchants.adapter.out.entity.MerchantGatewayCredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MerchantGatewayCredentialsJpaRepository extends JpaRepository<MerchantGatewayCredentialsEntity, UUID> {

    Optional<MerchantGatewayCredentialsEntity> findByMerchantId(UUID merchantId);
}
