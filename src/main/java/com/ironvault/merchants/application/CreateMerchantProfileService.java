package com.ironvault.merchants.application;

import com.ironvault.merchants.domain.model.MerchantProfile;
import com.ironvault.merchants.domain.port.in.CreateMerchantProfileUseCase;
import com.ironvault.merchants.domain.port.out.MerchantProfileRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class CreateMerchantProfileService implements CreateMerchantProfileUseCase {

    private final MerchantProfileRepositoryPort repositoryPort;

    public CreateMerchantProfileService(MerchantProfileRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    @Transactional
    public MerchantProfile execute(UUID userId, String businessName,
                                   String cpfOuCnpj, String segment,
                                   String phone, String website) {
        if (repositoryPort.existsByUserId(userId)) {
            throw new IllegalStateException("Merchant profile already exists for userId: " + userId);
        }

        MerchantProfile profile = MerchantProfile.create(userId, businessName, cpfOuCnpj, segment, phone, website);
        log.warn("Creating merchant profile for userId={}", userId);

        return repositoryPort.save(profile);
    }
}
