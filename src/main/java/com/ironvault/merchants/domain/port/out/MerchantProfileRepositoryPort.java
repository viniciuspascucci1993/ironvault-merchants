package com.ironvault.merchants.domain.port.out;

import com.ironvault.merchants.domain.model.MerchantProfile;

import java.util.Optional;
import java.util.UUID;

public interface MerchantProfileRepositoryPort {

    MerchantProfile save(MerchantProfile profile);
    Optional<MerchantProfile> findByUserId(UUID userId);
    boolean existsByUserId(UUID userId);
}
