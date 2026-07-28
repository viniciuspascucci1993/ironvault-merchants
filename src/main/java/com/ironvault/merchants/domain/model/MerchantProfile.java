package com.ironvault.merchants.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class MerchantProfile {

    private UUID id;
    private UUID userId;
    private String businessName;
    private String cpfOuCnpj;
    private String segment;
    private String phone;
    private String website;
    private LocalDateTime createdAt;

    public MerchantProfile() { }

    public static MerchantProfile create(UUID userId, String businessName,
                                  String cpfOuCnpj, String segment,
                                  String phone, String website) {

        return new MerchantProfile(
            UUID.randomUUID(),
                userId,
                businessName,
                cpfOuCnpj,
                segment,
                phone,
                website,
                LocalDateTime.now()
        );
    }

    public MerchantProfile(UUID id, UUID userId,
                           String businessName,
                           String cpfOuCnpj,
                           String segment, String phone,
                           String website,
                           LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.businessName = businessName;
        this.cpfOuCnpj = cpfOuCnpj;
        this.segment = segment;
        this.phone = phone;
        this.website = website;
        this.createdAt = createdAt;
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
