package com.ironvault.merchants.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMerchantProfileRequest {

    @NotBlank(message = "Business name is required")
    private String businessName;
    private String cpfOuCnpj;
    private String segment;
    private String phone;
    private String website;
}
