package com.ironvault.merchants.adapter.out.persistence;

import com.ironvault.merchants.adapter.out.entity.MerchantProfileEntity;
import com.ironvault.merchants.domain.model.MerchantProfile;
import com.ironvault.merchants.domain.port.out.MerchantProfileRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class MerchantProfileRepositoryAdapter implements MerchantProfileRepositoryPort {

    private final MerchantProfileJpaRepository jpaRepository;

    public MerchantProfileRepositoryAdapter(MerchantProfileJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public MerchantProfile save(MerchantProfile profile) {
        MerchantProfileEntity entity = toEntity(profile);
        return toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<MerchantProfile> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).map(this::toDomain);
    }

    @Override
    public boolean existsByUserId(UUID userId) {
        return jpaRepository.existsByUserId(userId);
    }

    private MerchantProfile toDomain(MerchantProfileEntity entity) {
        return new MerchantProfile(
                entity.getId(),
                entity.getUserId(),
                entity.getBusinessName(),
                entity.getCpfOuCnpj(),
                entity.getSegment(),
                entity.getPhone(),
                entity.getWebsite(),
                entity.getCreatedAt()
        );
    }

    private MerchantProfileEntity toEntity(MerchantProfile profile) {
        return new MerchantProfileEntity(
                profile.getId(),
                profile.getUserId(),
                profile.getBusinessName(),
                profile.getCpfOuCnpj(),
                profile.getSegment(),
                profile.getPhone(),
                profile.getWebsite(),
                profile.getCreatedAt()
        );
    }
}
