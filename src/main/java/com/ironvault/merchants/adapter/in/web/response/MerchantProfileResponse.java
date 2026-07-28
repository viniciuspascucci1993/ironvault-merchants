package com.ironvault.merchants.adapter.in.web.response;

import com.ironvault.merchants.domain.model.MerchantProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantProfileResponse {

    private UUID id;
    private UUID userId;
    private String businessName;
    private String cpfOuCnpj;
    private String segment;
    private String phone;
    private String website;
    private LocalDateTime createdAt;

    public static MerchantProfileResponse of(MerchantProfile profile) {
        MerchantProfileResponse response = new MerchantProfileResponse();
        response.setId(profile.getId());
        response.setUserId(profile.getUserId());
        response.setBusinessName(profile.getBusinessName());
        response.setCpfOuCnpj(profile.getCpfOuCnpj());
        response.setSegment(profile.getSegment());
        response.setPhone(profile.getPhone());
        response.setWebsite(profile.getWebsite());
        response.setCreatedAt(profile.getCreatedAt());

        return response;
    }
}
