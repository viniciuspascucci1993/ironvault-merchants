package com.ironvault.merchants.adapter.in.web;

import com.ironvault.merchants.adapter.in.web.request.CreateMerchantProfileRequest;
import com.ironvault.merchants.adapter.in.web.response.MerchantProfileResponse;
import com.ironvault.merchants.domain.port.in.CreateMerchantProfileUseCase;
import com.ironvault.merchants.domain.port.in.GetMerchantProfileUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/merchants")
public class MerchantProfileController {

    private final CreateMerchantProfileUseCase createMerchantProfileUseCase;
    private final GetMerchantProfileUseCase getMerchantProfileUseCase;

    public MerchantProfileController(CreateMerchantProfileUseCase createMerchantProfileUseCase,
                                     GetMerchantProfileUseCase getMerchantProfileUseCase) {
        this.createMerchantProfileUseCase = createMerchantProfileUseCase;
        this.getMerchantProfileUseCase = getMerchantProfileUseCase;
    }

    @PostMapping("/profile")
    public ResponseEntity<MerchantProfileResponse> create(
            @Valid @RequestBody CreateMerchantProfileRequest request,
            @RequestHeader("X-User-Id") UUID userId) {

        var profile = createMerchantProfileUseCase.execute(
                userId,
                request.getBusinessName(),
                request.getCpfOuCnpj(),
                request.getSegment(),
                request.getPhone(),
                request.getWebsite()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(MerchantProfileResponse.of(profile));
    }

    @GetMapping("/profile")
    public ResponseEntity<MerchantProfileResponse> get(
            @RequestHeader("X-User-Id") UUID userId) {

        return getMerchantProfileUseCase.execute(userId)
                .map(profile -> ResponseEntity.ok(MerchantProfileResponse.of(profile)))
                .orElse(ResponseEntity.notFound().build());
    }
}
