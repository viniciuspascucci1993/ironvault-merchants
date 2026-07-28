package com.ironvault.merchants.adapter.out.persistence;

import com.ironvault.merchants.adapter.out.entity.MerchantProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MerchantProfileJpaRepository extends JpaRepository<MerchantProfileEntity, UUID> {

    Optional<MerchantProfileEntity> findByUserId(UUID userId);
    boolean existsByUserId(UUID userId);
}
