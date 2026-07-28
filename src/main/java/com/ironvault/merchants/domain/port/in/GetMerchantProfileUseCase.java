package com.ironvault.merchants.domain.port.in;

import com.ironvault.merchants.domain.model.MerchantProfile;

import java.util.Optional;
import java.util.UUID;

public interface GetMerchantProfileUseCase {

    Optional<MerchantProfile> execute(UUID userId);
}
