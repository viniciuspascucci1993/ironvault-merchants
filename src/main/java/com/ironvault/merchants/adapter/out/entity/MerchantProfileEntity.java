package com.ironvault.merchants.adapter.out.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "merchant_profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantProfileEntity {

    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "cpf_ou_cnpj")
    private String cpfOuCnpj;

    @Column
    private String segment;

    @Column
    private String phone;

    @Column
    private String website;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
