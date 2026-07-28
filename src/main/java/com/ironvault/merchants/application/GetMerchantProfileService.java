package com.ironvault.merchants.application;

import com.ironvault.merchants.domain.model.MerchantProfile;
import com.ironvault.merchants.domain.port.in.GetMerchantProfileUseCase;
import com.ironvault.merchants.domain.port.out.MerchantProfileRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class GetMerchantProfileService implements GetMerchantProfileUseCase {

    private final MerchantProfileRepositoryPort repositoryPort;

    public GetMerchantProfileService(MerchantProfileRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Optional<MerchantProfile> execute(UUID userId) {
        return repositoryPort.findByUserId(userId);
    }
}
