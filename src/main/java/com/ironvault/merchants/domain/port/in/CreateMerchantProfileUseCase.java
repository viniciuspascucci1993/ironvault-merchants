package com.ironvault.merchants.domain.port.in;

import com.ironvault.merchants.domain.model.MerchantProfile;

import java.util.UUID;

public interface CreateMerchantProfileUseCase {

    MerchantProfile execute(UUID userId, String businessName, String cpfOuCnpj,
                            String segment, String phone, String website);
}
